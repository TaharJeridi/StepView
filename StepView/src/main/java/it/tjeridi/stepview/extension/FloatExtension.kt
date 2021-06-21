package it.tjeridi.stepview.extension

internal fun Float?.isGreaterThanZeroOrDefault(defaultValue:Float = 1F):Float{
    this?.let {
        if(it > 0F){
            return it
        }
    }
    return defaultValue
}

internal fun Float?.isNotNullOrZero():Float{
    this?.let {
        return it
    }
    return 0F
}

internal fun Float?.isNotNullOrDefault(defaultValue: Float):Float{
    this?.let {
        return it
    }
    return defaultValue
}