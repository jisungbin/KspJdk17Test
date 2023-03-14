@file:Suppress("unused")

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated

class SampleProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return TestProcessor(environment)
    }
}

class TestProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        try {
            val outStream = environment.codeGenerator.createNewFile(
                dependencies = Dependencies(false),
                packageName = "",
                fileName = "test",
            )
            outStream.bufferedWriter().use { it.write("// ${getDummyStringWrapper().value}!") }
        } catch (ignored: FileAlreadyExistsException) {
        }
        return emptyList()
    }
}
