package org.renci.chemotext

import java.time.{LocalDate, Year, YearMonth}

import utest._

import scala.util.Success

/**
  * Unit tests for the PubMedArticleWrapper class.
  */
@SuppressWarnings(
  Array(
    "org.wartremover.warts.TryPartial",       // We use Try.get() in a
    "org.wartremover.warts.NonUnitStatements" // non-unit statements to test whether parsing fails correctly.
  )
)
object PubMedArticleWrapperUnitTests extends TestSuite {
  val tests = Tests {
    test("#parseDate") {
      test("Test processing of valid dates") {
        val datesTested = Seq(
          (Year.of(2006), <PubDate><Year>2006</Year></PubDate>),
          (YearMonth.of(2006, 10), <PubDate><Year>2006</Year><Month>Oct</Month></PubDate>),
          (
            LocalDate.of(2006, 10, 21),
            <PubDate><Year>2006</Year><Day>21</Day><Month>Oct</Month></PubDate>
          ),
          (Year.of(1998), <PubDate><MedlineDate>1998 Dec-1999 Jan</MedlineDate></PubDate>),
          (
            LocalDate.of(2006, 10, 21),
            <PubDate><Year>2006</Year><Day>21</Day><Month>10</Month></PubDate>
          ),
          (Year.of(2016), <PubDate><MedlineDate>Summer 2016</MedlineDate></PubDate>)
        )

        datesTested.foreach({
          case (expected, xmlNode) =>
            assert(PubMedArticleWrapper.parseDate(xmlNode) == Success(expected))
        })
      }
      test("Test processing of invalid dates") {
        assert(
          (intercept[IllegalArgumentException] {
            PubMedArticleWrapper
              .parseDate(<PubDate><MedlineDate>199 Dec-199 Jan</MedlineDate></PubDate>)
              .get
          }).getMessage == "Could not parse XML node as date: <PubDate><MedlineDate>199 Dec-199 Jan</MedlineDate></PubDate>"
        )
        assert((intercept[IllegalArgumentException] {
          PubMedArticleWrapper.parseDate(<PubDate></PubDate>).get
        }).getMessage == "Could not parse XML node as date: <PubDate></PubDate>")
        assert(
          (intercept[RuntimeException] {
            PubMedArticleWrapper.parseDate(<PubDate><Year>2019</Year><Day>12</Day></PubDate>).get
          }).getMessage == "Could not extract month from node: <PubDate><Year>2019</Year><Day>12</Day></PubDate>"
        )
      }
    }
  }
}
