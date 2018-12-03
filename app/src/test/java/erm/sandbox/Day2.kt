package erm.sandbox

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Day2 {
  @Test
  fun test2() {
    //assertEquals(5704, multiplyInstanceCountsOf2And3(actualList))
    assertEquals("umdryabviapkozistwcnihjqx", findMatchingThenCommonBoxIDLetters2(actualList))
    //umdryabviapkozistwcnihjqx
    //umdryabviapkozistwcnihjqx
  }

  private fun findMatchingThenCommonBoxIDLetters2(characters: List<String>): String {
    var normalizedCharacters = characters.map {
      it.trim()
          .toLowerCase()
    }

    normalizedCharacters.forEach { boxId1 ->
      normalizedCharacters.forEach { boxId2 ->
        if (boxId1 != boxId2//omit the same one
            && boxId1.length == boxId2.length
        ) {
          var commonCharsAtSamePosition = ""
          for (i in 0 until boxId1.length) {
            if (boxId1[i] == boxId2[i]) {
              commonCharsAtSamePosition += boxId1[i]
            }
          }
          if (commonCharsAtSamePosition.length == boxId1.length - 1) {
            return commonCharsAtSamePosition
          }
        }
      }
    }
    return ""
  }

  private fun findMatchingThenCommonBoxIDLetters(characters: List<String>): String {
    var normalizedCharacters = characters.map {
      it.trim()
          .toLowerCase()
    }

    normalizedCharacters.forEach { boxId1 ->
      normalizedCharacters.forEach { boxId2 ->
        if (boxId1 != boxId2//omit the same one
            && boxId1.length == boxId2.length
        ) {
          var differences = 0
          var differenceIndex = 0
          for (i in 0 until boxId1.length) {
            if (boxId1[i] != boxId2[i]) {
              differences++
              if (differences > 1) break
              differenceIndex = i
            }
          }
          if (differences == 1) {
            var charList = boxId1.toCharArray()
                .toMutableList()
            charList.removeAt(differenceIndex)
            return charList.joinToString("")
          }
        }
      }
    }
    return ""
  }

  private fun multiplyInstanceCountsOf2And3(characters: List<String>): Int {
    var instancesOf2 = 0
    var instancesOf3 = 0

    characters.map {
      it.toLowerCase()
          .trim()
    }
        .forEach {
          val groupByLetters = it.toCharArray()
              .groupBy { it }
          instancesOf2 += if (groupByLetters.entries.any { it.value.count() == 2 }) 1 else 0
          instancesOf3 += if (groupByLetters.entries.any { it.value.count() == 3 }) 1 else 0
        }

    return instancesOf2 * instancesOf3
  }

  val testList = listOf(
      "abcde",
      "fghij",
      "klmno",
      "pqrst",
      "fauij",
      "axcye",
      "bbcde"
  )

  val actualList = listOf(
      "umdryebvlapkozostecnihjexg",
      "amdryebalapkozfstwcnrhjqxg",
      "umdcyebvlapaozfstwcnihjqgg",
      "ymdryrbvlapkozfstwcuihjqxg",
      "umdrsebvlapkozxstwcnihjqig",
      "umdryibvlapkohfstwcnfhjqxg",
      "umdryebvqapkozfatwcnihjqxs",
      "umzrpebvlapkozfshwcnihjqxg",
      "fmhryebvlapkozfstwckihjqxg",
      "umdryebvlahkozfstwcnizjrxg",
      "qmdryebvlapkozfslwcnihgqxg",
      "umdiyebjlapknzfstwcnihjqxg",
      "umdryebvlapkoqfstwcaihvqxg",
      "cmdryebvlapkpzfstwcnihjvxg",
      "umdryebvlakkozfstwcgihjixg",
      "umdryebvlasjozfstwcnihqqxg",
      "umdryebvladkozfsvwcnifjqxg",
      "umdrlebvlapaozfstwcniwjqxg",
      "umdryebvlhpkozrstwsnihjqxg",
      "umdryebvcapkozfqtwcnihjrxg",
      "ubdrykbvlapkowfstwcnihjqxg",
      "umdryebvldpkozfstwcnihtqsg",
      "umdryebvlapaozyutwcnihjqxg",
      "umdryibvlapkozfstdfnihjqxg",
      "umdryebvlapgozkstwznihjqxg",
      "umdrxebvlapkozfstwcngxjqxg",
      "umdryekvlapkozfstwclchjqxg",
      "nmdryebvlapkozjsewcnihjqxg",
      "umdryebvyapkozfstfcniheqxg",
      "umdfyebvlapkozfstwcnhhjpxg",
      "umdryelylupkozfstwcnihjqxg",
      "smdryebvlqpkozfstwcnihjdxg",
      "umdryebvlapaozfsuwcnihjqxc",
      "umdryebvlrzkozrstwcnihjqxg",
      "umdbycbvlapkojfstwcnihjqxg",
      "umdryebvlapkonfstwpnirjqxg",
      "uecryebvlapkozfstwcnihpqxg",
      "uqdryebvltpkozfstwcnihrqxg",
      "umdryebvlqsknzfstwcnihjqxg",
      "cmdryebvlapkocfstwcvihjqxg",
      "umdrkebvlapkozqsfwcnihjqxg",
      "umdryabveapkoifstwcnihjqxg",
      "ummrnehvlapkozfstwcnihjqxg",
      "umdryebvlxpkozfstwqnihjtxg",
      "umdryebvlagkozastwcnihjqxh",
      "umdryebvlatkozzhtwcnihjqxg",
      "umdryebvlcpkozfstwrnihjqvg",
      "umdryebvlapkozfsnwcnrhjcxg",
      "umdzyebvlypkozfstwcnibjqxg",
      "nmdryebvlvpkozbstwcnihjqxg",
      "uwdryebvlipkozfstwcnihvqxg",
      "umdraebvlavkozfstwcnihjqwg",
      "umdeyebvlspbozfstwcnihjqxg",
      "umdryxlvlapkozfstwcnihjqxu",
      "umdryegvlapkqzfstwcnirjqxg",
      "umdrupbvlapkozfstwcnihjqog",
      "imxryebvlapkxzfstwcnihjqxg",
      "umdrfebvlapkozowtwcnihjqxg",
      "umdreebvlapkozmstwczihjqxg",
      "undryebdlapkozbstwcnihjqxg",
      "umdryebvlapkpzfetwcnihjqxb",
      "ymdnyebvlapkozfstwinihjqxg",
      "umdryebvaapkozfstwcnihyqqg",
      "umdryebvlapkzzwsrwcnihjqxg",
      "umdrkebvlapkmzfskwcnihjqxg",
      "umdrmebvlapkozfsvwcnidjqxg",
      "umdlyehvlapkozfstwcnihjqkg",
      "umnryebvlrpkozfstwjnihjqxg",
      "uqdryebvlapxozfsawcnihjqxg",
      "vmdruebvlapkozfstwcnihjqqg",
      "umdryabviapkozistwcnihjqxg",
      "umdryebvlapkzzfstwfnihkqxg",
      "uvdryebvlapkozfsxwcuihjqxg",
      "umdlhebvlapkozfstwcnvhjqxg",
      "umdreebvlapkopfstjcnihjqxg",
      "umdryebvlazkomfstwynihjqxg",
      "kmdryebulapkoznstwcnihjqxg",
      "umdryebvxakkozfstwinihjqxg",
      "ukdryobvlapkozistwcnihjqxg",
      "umdryebveapkozfstwcnthjqgg",
      "mmdrtebvlapcozfstwcnihjqxg",
      "umdryebvlapkolistwnnihjqxg",
      "umdryebxlapkozfatwcnihjqxx",
      "uxdryebvlapkozfstwhniheqxg",
      "ufdryebvzapkozfstwcnbhjqxg",
      "amdryhbvlapkozfstwcnifjqxg",
      "umqryebvlaphozfstwcnihjqxn",
      "umdryebvlapkosfstfcnihjqxe",
      "gmkryebvlapkozfstwcnihjmxg",
      "umdrnebvlkpkozfstwcnihjnxg",
      "umdryebvrapkozfstmcndhjqxg",
      "umdryebvmapkozfstichihjqxg",
      "umdryesvnapkozestwcnihjqxg",
      "umeryhbvlapkozfstfcnihjqxg",
      "umdryedvbapkozfstwcnihqqxg",
      "umdryebllapzozfstwcnihjvxg",
      "umdcyebvlzdkozfstwcnihjqxg",
      "umdrybbvlapkbvfstwcnihjqxg",
      "umdrytbglapkozfsthcnihjqxg",
      "umdryebvlkpkozfsteclihjqxg",
      "umdntebvlapkmzfstwcnihjqxg",
      "lkdryebveapkozfstwcnihjqxg",
      "ymdryubvlapkozfstwbnihjqxg",
      "tmrryebvlapkozfstwcnqhjqxg",
      "umdryeovlaekonfstwcnihjqxg",
      "umiryeuvlapkozfstwcnihjwxg",
      "umdryebvlspvozwstwcnihjqxg",
      "umdrtebvlapkoznxtwcnihjqxg",
      "umvryebvlaphozfstwcnahjqxg",
      "umdryebvlapkozfstinniajqxg",
      "umdryebqlapkozfctwcnihjqxx",
      "umdryebvlapkbzfptwcnihjqvg",
      "umdryabviapkozistwcnihjqxd",
      "umdryrbvlapkezfstscnihjqxg",
      "umhryebvlapkozfstacnihxqxg",
      "umdxyelvlapkozfitwcnihjqxg",
      "umdryevvuapkozfstwcnihtqxg",
      "uydrypbvxapkozfstwcnihjqxg",
      "umdryebvlapkopfstwcnihzqxo",
      "uedryebvlapkozistwceihjqxg",
      "umdiyebvlapkozfgtwcnihjqxv",
      "ymdryebvlapkozfsticniqjqxg",
      "umbrkebvlapkozfslwcnihjqxg",
      "umdryebliapkozbstwcnihjqxg",
      "umvryebolapkozfstwcnihjqig",
      "umdryeavbackozfstwcnihjqxg",
      "umdryfbvlapsozfstwcnihaqxg",
      "umdqyebvlapkozfjtgcnihjqxg",
      "umdrjebvlaqkozfstwcyihjqxg",
      "umdryebklaqkozrstwcnihjqxg",
      "umdryebvpapkozfstwcpihjqjg",
      "uydryebhlawkozfstwcnihjqxg",
      "umdyyebvlapkozfstwcykhjqxg",
      "umdryebvlapkozfstwcnitjnxh",
      "umdzyebvlapkozfstwcnehyqxg",
      "mmcryebvlapkozfstwinihjqxg",
      "umdryebvlapuozfstwmvihjqxg",
      "umdryfbvlapkozqstwcnihjmxg",
      "umdryebslapsozfhtwcnihjqxg",
      "umdtyemvlapmozfstwcnihjqxg",
      "umdrxevvlapkozfytwcnihjqxg",
      "umdahebvlapjozfstwcnihjqxg",
      "umdryebvlapkozfstacnivjqxb",
      "umdryebvlzpkozfjtwcnihjyxg",
      "umdryebvlaqkozfstwcnisjqxu",
      "umdrydbvlapkozfsuwcnihjlxg",
      "umdryebvlapkomrstwcnihjqkg",
      "umdryebvlapcozfstmcnwhjqxg",
      "umdryebvlahkozfstwcibhjqxg",
      "gmdrzebvlapkozlstwcnihjqxg",
      "umdryebvlapkezfsswcnrhjqxg",
      "umdryebvlapkoqfitwcgihjqxg",
      "umdrnebvlapkozfsiwcninjqxg",
      "umdryebvlapkozfsrwckohjqxg",
      "umdryebtlapkomfstwcnihjexg",
      "umdryxbvlapjozfstwcnihoqxg",
      "umdpyebvlapkosustwcnihjqxg",
      "umdryebvlapkvzfawwcnihjqxg",
      "umhnyebvlaikozfstwcnihjqxg",
      "umdryebvlagkozfstvknihjqxg",
      "uodryebjlapkoxfstwcnihjqxg",
      "umdryefdlapkozfstwcnyhjqxg",
      "umprmebvtapkozfstwcnihjqxg",
      "umdhyebvlapoozfstwcnihjqgg",
      "uddryebvidpkozfstwcnihjqxg",
      "umdryebtlapkozfetwfnihjqxg",
      "umdbyebolapkozfstwcoihjqxg",
      "umdryebvlapkonfstwcnihjpxo",
      "umdryebvlapkohfstwcnihjqwk",
      "umdryebolalkkzfstwcnihjqxg",
      "updryebvxapkozfstwcnshjqxg",
      "umdryebvlapkovfktwcnuhjqxg",
      "umdrqrbvlppkozfstwcnihjqxg",
      "umdrylgvlapkozfstwrnihjqxg",
      "umdryebvlapkozfstxcnihbqig",
      "uvdryeevlappozfstwcnihjqxg",
      "zmdryebvlapkozfstwcnihqqxt",
      "umdryebvlapvozfstwenihiqxg",
      "umdryebvlbpkozfsgwcnihjlxg",
      "umdryhbvlapkozfstwcnihtqxw",
      "umdreecvlapkozwstwcnihjqxg",
      "umwryebvlapkoztsmwcnihjqxg",
      "ukdryebvfapkozrstwcnihjqxg",
      "umdrylbdlamkozfstwcnihjqxg",
      "umdryebvlapoozwsmwcnihjqxg",
      "umdryebvlapkozfqtwcnnzjqxg",
      "umdryekvlapktzfstwcnohjqxg",
      "umdryebvlapkozfstwcnihjwqo",
      "umdrrebflapkogfstwcnihjqxg",
      "umdryevvlapkozfztwctihjqxg",
      "umdrybbvlapkozfstwcnihxaxg",
      "umdryebvlapkozfsowcnphjqag",
      "smdryebvlapbozfitwcnihjqxg",
      "umdryebvtapiozfstwcnihjqxe",
      "umdryebjlakkozfstwccihjqxg",
      "umdryebvlapdozfshwckihjqxg",
      "umnryebvlapiozfstwcnihlqxg",
      "umdrycbvlapkjzfsnwcnihjqxg",
      "umdryebvyaprozjstwcnihjqxg",
      "ucdryebvlapkozfstwomihjqxg",
      "umdryebvlagklzfstwcnihjqyg",
      "umdryebvladkozfstwcnihjqjh",
      "umdrwebvlapkozfstwdnicjqxg",
      "umdryebvlapkmzfstwcniheqxr",
      "umdryebvlapkjzfstwcviheqxg",
      "umdrvebvlapkozfstwcbihjqmg",
      "umdrfebvlapkoffstwcnihsqxg",
      "umdryebvtarkazfstwcnihjqxg",
      "umdryebvlapkozfstwcfihjcng",
      "umdryebvlapkktostwcnihjqxg",
      "uedryeevlapkozfstwcniijqxg",
      "bmdryebylapkozfstwcnihjqog",
      "umdryebvlmpkoztstwcnihjqeg",
      "umdryepvlarkohfstwcnihjqxg",
      "uwdryebvlapklzfstzcnihjqxg",
      "umdryebklapkozfsswcbihjqxg",
      "umdtyeavlapkozfstwsnihjqxg",
      "umdryebvaapkozfhtfcnihjqxg",
      "umdrpebvlapuozfstwvnihjqxg",
      "umdryebvlapkozffmwcniijqxg",
      "uqdpyebvlapkozfstwfnihjqxg",
      "umdryebvlapuozdstwcnihjhxg",
      "tmdryhbvlapkozfptwcnihjqxg",
      "umdryevvmapkozfstwcnihjgxg",
      "umdryeuvlapmozfstwcnihjwxg",
      "umdryebqlzpkozfbtwcnihjqxg",
      "umdryebvsapkozystwcniqjqxg",
      "imdryebvlapkozfscwinihjqxg",
      "umdryebvlzpkozustwcnmhjqxg",
      "umdrypbvlapbozfsnwcnihjqxg",
      "bmdryebvlapqoznstwcnihjqxg",
      "umdrfebvlapaozfstwcnihxqxg",
      "umdiyebvxapkozfstwcnchjqxg",
      "umdrygbvlapkozfstwcnizjqxz",
      "amdryedvlapkozfstwcnihfqxg",
      "umdryebvvapzozfstwcnihjgxg",
      "undryebvlapkzzfstjcnihjqxg",
      "umdryvbvlapgozfrtwcnihjqxg",
      "umdrkebvlapkozfstwcnihihxg",
      "umdryebvrppkozfsowcnihjqxg",
      "umdryebvlapktzfsdwclihjqxg",
      "otdrdebvlapkozfstwcnihjqxg",
      "mmdryebvlazkozfxtwcnihjqxg",
      "umdryebvlapkozfsbwtnihjqxa",
      "imqryebvrapkozfstwcnihjqxg",
      "umdryebvlrpkozfscwcnihjqlg",
      "uedryebvlapkoznsvwcnihjqxg",
      "umdryebvlqpkozfstscnihjqxj",
      "umerycbvlapkozfstwcnihjqxh",
      "umdkykbvlapjozfstwcnihjqxg"
  )

}
