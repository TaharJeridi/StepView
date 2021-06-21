package it.tjeridi.stepview.extension

internal fun Int?.isGreaterThanOneOrZeroOrDefault(defaultValue:Int = 1):Int{
    this?.let {
        if(it > 1 || it == 0){
            return it
        }
    }
    return defaultValue
}

internal fun Int?.isNotNullOrZero():Int{
    this?.let {
        return it
    }
    return 0
}
