package io.gitlab.arturbosch.detekt.rules.bugs

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.assertj.core.api.Assertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class LateinitUsageSpec : Spek({

	given("a kt file with lateinit usages") {
		val code = """
			package foo

			class Test {
				@Ignore lateinit var test: Int
			}
		"""

		it("should report lateinit usages") {
			val findings = LateinitUsage().lint(code)
			Assertions.assertThat(findings).hasSize(1)
		}

		it("should not report lateinit properties annotated @Ignore") {
			val findings = LateinitUsage(TestConfig(mapOf(LateinitUsage.EXCLUDE_ANNOTATED_PROPERTIES to "Ignore"))).lint(code)
			Assertions.assertThat(findings).hasSize(0)
		}

		it("should not exclude lateinit properties not matching the exclude pattern") {
			val findings = LateinitUsage(TestConfig(mapOf(LateinitUsage.EXCLUDE_ANNOTATED_PROPERTIES to "IgnoreThis"))).lint(code)
			Assertions.assertThat(findings).hasSize(1)
		}
	}
})
