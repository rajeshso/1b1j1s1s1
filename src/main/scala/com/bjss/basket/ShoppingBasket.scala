package com.bjss.basket

/**
  * Created by Rajesh on 30-Apr-17.
  */

import java.lang.Math._

import com.bjss.items._
import com.bjss.offer.{DisableOffer, EnableOffer, Offer}

import scala.math.BigDecimal.RoundingMode.HALF_UP

class ShoppingBasket(offers: Offer = EnableOffer)  {

  val prices: Map[Item, Double] = Map(Soup -> 0.65, Bread -> 0.80, Milk -> 1.3, Apple -> 1.0)
  private var items = List.empty[Item]

  def +=(item: Item): Unit = items = items :+ item

  def itemCount: Int = items.size

  def basketItemsTotalPrice: Double = {
    def totalForApples = items.count(_== Apple) * prices(Apple) * 0.9

    def totalForSoup = items.count(_== Soup) * prices(Soup)

    def totalForMilk = (items.count(_== Milk) * prices(Milk))

    def totalForBread = (items.count(_ == Bread) * prices(Bread)) - (breadsEligibleForHalfPrice * prices(Bread) / 2)

    def breadsEligibleForHalfPrice = {
      val countSoupPairs = items.count(_ == Soup) / 2
      val countBreads = items.count(_ == Bread)
      min(countBreads, countSoupPairs)
    }

    offers match {
      case EnableOffer =>
        BigDecimal(totalForApples + totalForSoup + totalForBread + totalForMilk).setScale(2, HALF_UP).toDouble
      case DisableOffer => {
        BigDecimal(items.foldLeft(0D) { (total, item) =>
          total + BigDecimal(prices(item)).setScale(2, HALF_UP).toDouble
        }).setScale(2, HALF_UP).toDouble
      }
    }
  }
}