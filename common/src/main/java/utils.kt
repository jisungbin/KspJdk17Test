interface StringBox {
    val value: String
}

private class StringBoxInstance : StringBox {
    override val value = "HI!"
}

fun getDummyStringWrapper(): StringBox {
    return StringBoxInstance()
}
