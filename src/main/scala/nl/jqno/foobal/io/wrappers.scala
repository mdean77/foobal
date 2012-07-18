package nl.jqno.foobal.io

import java.io.IOException

import java.io.InputStream
import java.net.URL

import scala.xml.XML

import org.joda.time.LocalDate


class Xml {
  @throws(classOf[IOException])
  def loadFile(fileName: String): scala.xml.Node = XML.loadFile(fileName)
  
  @throws(classOf[IOException])
  def saveFile(fileName: String, node: scala.xml.Node): Unit = XML.save(fileName, node)
}

class Url(spec: String) {
  private val url = new URL(spec)
  
  def openConnection: UrlConnection = new UrlConnection(url)
}

class UrlConnection(url: URL) {
  private val con = url.openConnection
  
  def setConnectTimeout(millis: Int) = con.setConnectTimeout(millis)
  
  def setReadTimeout(millis: Int) = con.setReadTimeout(millis)
  
  @throws(classOf[IOException])
  def getInputStream: InputStream = con.getInputStream
}

class DateFactory {
  def today = new LocalDate
}