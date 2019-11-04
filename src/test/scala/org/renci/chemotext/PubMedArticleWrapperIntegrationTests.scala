package org.renci.chemotext

import java.time.{LocalDate, Year, YearMonth}

import utest._

import scala.xml.XML

/**
  * Integration tests of PubmedArticleWrapper.
  */
object PubMedArticleWrapperIntegrationTests extends TestSuite {
  val examplesForTests =
    XML.loadFile(getClass.getResource("/pubmedXML/examplesForTests.xml").getPath)
  val pubmedArticles = examplesForTests \ "PubmedArticle"

  val tests = Tests {
    test("An example with day, month and year") {
      val wrappedArticle = new PubMedArticleWrapper(pubmedArticles(0))

      assert(wrappedArticle.pmid == "11237011")
      assert(
        wrappedArticle.asString == "Initial sequencing and analysis of the human genome. The human genome holds an extraordinary trove of information about human development, physiology, medicine and evolution. Here we report the results of an international collaboration to produce and make freely available a draft sequence of the human genome. We also present an initial analysis of the data, describing some of the insights that can be gleaned from the sequence. Genetics, Medical CpG Islands Public Sector Gene Duplication Proteins Databases, Factual Proteome Proteins genetics Repetitive Sequences, Nucleic Acid Forecasting GC Rich Sequence Drug Industry Genes Sequence Analysis, DNA methods Humans Animals DNA Transposable Elements Private Sector Mutation Conserved Sequence Genome, Human RNA RNA genetics Evolution, Molecular Human Genome Project Chromosome Mapping Species Specificity Genetic Diseases, Inborn "
      )
      assert(
        wrappedArticle.allMeshTermIDs == Set(
          "D002874",
          "D019143",
          "D017124",
          "D005826",
          "D013045",
          "D005796",
          "D018899",
          "D016208",
          "D012313",
          "D000818",
          "D015894",
          "D017149",
          "D006801",
          "D004345",
          "D020862",
          "D020543",
          "D017422",
          "D004251",
          "D017150",
          "D009154",
          "D005544",
          "D016045",
          "Q000235",
          "D011506",
          "Q000379",
          "D020440",
          "D012091",
          "D030342"
        )
      )
      assert(wrappedArticle.pubDates == Seq(LocalDate.of(2001, 2, 15)))
      assert(wrappedArticle.revisedDates == Seq(LocalDate.of(2019, 2, 8)))
    }

    test("An example with month and year") {
      val wrappedArticle = new PubMedArticleWrapper(pubmedArticles(1))

      assert(wrappedArticle.pmid == "17060194")
      assert(
        wrappedArticle.asString == "DNA barcoding and taxonomy in Diptera: a tale of high intraspecific variability and low identification success. DNA barcoding and DNA taxonomy have recently been proposed as solutions to the crisis of taxonomy and received significant attention from scientific journals, grant agencies, natural history museums, and mainstream media. Here, we test two key claims of molecular taxonomy using 1333 mitochondrial COI sequences for 449 species of Diptera. We investigate whether sequences can be used for species identification (\"DNA barcoding\") and find a relatively low success rate (< 70%) based on tree-based and newly proposed species identification criteria. Misidentifications are due to wide overlap between intra- and interspecific genetic variability, which causes 6.5% of all query sequences to have allospecific or a mixture of allo- and conspecific (3.6%) best-matching barcodes. Even when two COI sequences are identical, there is a 6% chance that they belong to different species. We also find that 21% of all species lack unique barcodes when consensus sequences of all conspecific sequences are used. Lastly, we test whether DNA sequences yield an unambiguous species-level taxonomy when sequence profiles are assembled based on pairwise distance thresholds. We find many sequence triplets for which two of the three pairwise distances remain below the threshold, whereas the third exceeds it; i.e., it is impossible to consistently delimit species based on pairwise distances. Furthermore, for species profiles based on a 3% threshold, only 47% of all profiles are consistent with currently accepted species limits, 20% contain more than one species, and 33% only some sequences from one species; i.e., adopting such a DNA taxonomy would require the redescription of a large proportion of the known species, thus worsening the taxonomic impediment. We conclude with an outlook on the prospects of obtaining complete barcode databases and the future use of DNA sequences in a modern integrative taxonomy. Electron Transport Complex IV DNA, Mitochondrial Sequence Analysis, DNA DNA, Mitochondrial chemistry Animals Electron Transport Complex IV chemistry genetics Base Sequence Genetic Variation Classification methods Diptera classification genetics Phylogeny Consensus Sequence Species Specificity "
      )
      assert(
        wrappedArticle.allMeshTermIDs == Set(
          "D004175",
          "D001483",
          "D013045",
          "D016384",
          "D000818",
          "D010802",
          "D004272",
          "D002965",
          "D017422",
          "D003576",
          "Q000737",
          "Q000145",
          "Q000235",
          "D014644",
          "Q000379"
        )
      )
      assert(wrappedArticle.pubDates == Seq(YearMonth.of(2006, 10)))
      assert(wrappedArticle.revisedDates == Seq(LocalDate.of(2008, 11, 21)))
    }

    test("An example with year only") {
      val wrappedArticle = new PubMedArticleWrapper(pubmedArticles(2))

      assert(wrappedArticle.pmid == "22859891")
      assert(
        wrappedArticle.asString == "From documents to datasets: A MediaWiki-based method of annotating and extracting species observations in century-old field notebooks. Part diary, part scientific record, biological field notebooks often contain details necessary to understanding the location and environmental conditions existent during collecting events. Despite their clear value for (and recent use in) global change studies, the text-mining outputs from field notebooks have been idiosyncratic to specific research projects, and impossible to discover or re-use. Best practices and workflows for digitization, transcription, extraction, and integration with other sources are nascent or non-existent. In this paper, we demonstrate a workflow to generate structured outputs while also maintaining links to the original texts. The first step in this workflow was to place already digitized and transcribed field notebooks from the University of Colorado Museum of Natural History founder, Junius Henderson, on Wikisource, an open text transcription platform. Next, we created Wikisource templates to document places, dates, and taxa to facilitate annotation and wiki-linking. We then requested help from the public, through social media tools, to take advantage of volunteer efforts and energy. After three notebooks were fully annotated, content was converted into XML and annotations were extracted and cross-walked into Darwin Core compliant record sets. Finally, these recordsets were vetted, to provide valid taxon names, via a process we call \"taxonomic referencing.\" The result is identification and mobilization of 1,068 observations from three of Henderson's thirteen notebooks and a publishable Darwin Core record set for use in other analyses. Although challenges remain, this work demonstrates a feasible approach to unlock observations from field notebooks that enhances their discovery and interoperability without losing the narrative context from which those observations are drawn.\"Compose your notes as if you were writing a letter to someone a century in the future.\"Perrine and Patton (2011).  "
      )
      assert(wrappedArticle.allMeshTermIDs == Set())
      assert(wrappedArticle.pubDates == Seq(Year.of(2012)))
      assert(wrappedArticle.revisedDates == Seq(LocalDate.of(2018, 11, 13)))
    }

    test("An example with a MedlineDate") {
      val wrappedArticle = new PubMedArticleWrapper(pubmedArticles(3))

      assert(wrappedArticle.pmid == "10542500")
      assert(
        wrappedArticle.asString == "Thirty years of service.  Parkinson Disease therapy United Kingdom Humans Organizational Objectives Information Services Societies "
      )
      assert(
        wrappedArticle.allMeshTermIDs == Set(
          "D012952",
          "D007255",
          "D006801",
          "D010300",
          "D006113",
          "Q000628",
          "D009937"
        )
      )
      assert(wrappedArticle.pubDates == Seq(Year.of(1998)))
      assert(wrappedArticle.revisedDates == Seq(LocalDate.of(2016, 11, 24)))
    }
  }
}