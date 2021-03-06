package nl.jqno.foobal.io

import java.io.IOException

import java.io.InputStream
import java.net.URL

import scala.xml.XML

import org.joda.time.LocalDate


class Xml {
  @throws(classOf[IOException])
  def loadFile(fileName: String): scala.xml.Node =
    XML.loadFile(fileName)
  
  @throws(classOf[IOException])
  def saveFile(fileName: String, node: scala.xml.Node) {
    XML.save(fileName, node)
  }
}

class Url(private val spec: String) {
  private val url = new URL(spec)
  
  def openConnection: UrlConnection = new UrlConnection(url)
  
  override final def equals(obj: Any): Boolean = obj match {
    case other: Url => spec == other.spec
    case _          => false
  }
  
  override final def hashCode: Int = spec.##
}

class UrlConnection(url: URL) {
  private val Connection = url.openConnection
  
  def setConnectTimeout(millis: Int) {
    Connection.setConnectTimeout(millis)
  }
  
  def setReadTimeout(millis: Int) {
    Connection.setReadTimeout(millis)
  }
  
  @throws(classOf[IOException])
  def getInputStream: InputStream =
    Connection.getInputStream
}

class DateFactory {
  def today: LocalDate = new LocalDate
}
