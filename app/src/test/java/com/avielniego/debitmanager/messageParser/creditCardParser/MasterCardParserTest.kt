package com.avielniego.debitmanager.messageParser.creditCardParser

import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.messageParser.MessageParser
import com.avielniego.debitmanager.messageParser.credirCardParser.MasterCardParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MasterCardParserTest {

    companion object {
        val NON_ISRACARD_COMPANY_NAME = "NON_ISRACARD_COMPANY_NAME"
        val ISRACARD_COMPANY_NAME = "Isracard"
        val MESSAGE_TEXT = "בכרטיסך 5324 אושרה עסקה ב-12/02 בסך 52.20 ש\"ח בריקושט 3000 בע\"מ קני. מידע נוסף ייקלט במערכות שלנו ויהיה ניתן לצפייה באתר בעוד 48 שעות https://digital.isracard.co.il/personalarea/login"
        val INVALID_MESSAGE_TEXT = "ב://digital.isracard.co.il/personalarea/login"
        val INVALID_SUM_MESSAGE_TEXT = "בכרטיסך 5324 אושרה עסקה ב-12/02 בסך פה רשום סכום ש\"ח בריקושט 3000 בע\"מ קני. מידע נוסף ייקלט במערכות שלנו ויהיה ניתן לצפייה באתר בעוד 48 שעות https://digital.isracard.co.il/personalarea/login"
    }

    @Test
    fun parse_withNonIsracardMessageText_ShouldReturnNull() {
        val cardParser = MasterCardParser(Message(NON_ISRACARD_COMPANY_NAME, MESSAGE_TEXT))
        assertNull (cardParser.parse())
    }

    @Test(expected = MessageParser.CouldNotParseMessage::class)
    fun parse_withInvalidMassage_shouldThrowException() {
        val cardParser = MasterCardParser(Message(ISRACARD_COMPANY_NAME, INVALID_MESSAGE_TEXT))
        cardParser.parse()
    }

    @Test(expected = MessageParser.CouldNotParseSumInMessage::class)
    fun parse_withInvalidSumInMassage_shouldThrowException() {
        val cardParser = MasterCardParser(Message(ISRACARD_COMPANY_NAME, INVALID_SUM_MESSAGE_TEXT))
        cardParser.parse()
    }

    @Test
    fun parse_withValidMassage_shouldReturnCorrectDebit() {
        val cardParser = MasterCardParser(Message(ISRACARD_COMPANY_NAME, MESSAGE_TEXT))
        val expected = Debit(ISRACARD_COMPANY_NAME, MESSAGE_TEXT, 52.2, "ריקושט 3000 בע\"מ קני")
        assertEquals(expected, cardParser.parse())
    }
}