package com.jinggao.mvp.app

import androidx.lifecycle.AndroidViewModel
import com.jinggao.mvp.app.base.NoViewModel
import java.lang.reflect.ParameterizedType

/**
 * @author jing.gao
 * @date 2021/3/10 13:03
 * @version 1.0
 */
class ClassUtils {

    companion object{

        @JvmStatic
         fun getViewModel(obj: Any): Class<*>? {
            val currentClass = obj.javaClass
            val tClass = getGenericClass(currentClass, AndroidViewModel::class.java)
            return if (tClass == null || tClass == AndroidViewModel::class.java || tClass == NoViewModel::class.java) {
                null
            } else tClass
        }

        private fun getGenericClass(klass: Class<*>, filterClass: Class<*>): Class<*>? {
            val type = klass.genericSuperclass
            if (type == null || type !is ParameterizedType) return null
            val types = type.actualTypeArguments
            for (t in types) {
                val tClass = t as Class<*>
                if (filterClass.isAssignableFrom(tClass)) {
                    return tClass
                }
            }
            return null
        }

    }


}