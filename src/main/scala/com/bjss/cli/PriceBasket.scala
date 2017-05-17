package com.bjss.cli

import com.bjss.basket.ShoppingBasket
import com.bjss.items.{Apple, Bread, Milk, Soup}


object PriceBasket {
  //The input can be Apple Apple or Apple Milk or Apple Apple Bread
  def main(inp: Array[String]) = {
    println(s"Items for the basket are ${inp mkString(" ")}")
    val items: Array[String] = inp
    println(s"Number of Items in the basket is ${items.length}")
    items.length match {
      case 0 => println("The usage of the program is scala PriceBasket item1 item2 item3 ...")
      case _ => {
        val basket = new ShoppingBasket
        items foreach (item => {
          var maybeItem = Vector(Apple, Milk, Soup, Bread).find(_.toString == item)
          if (maybeItem.nonEmpty) basket += maybeItem.get
        })
        println(s"Total price is ${basket.basketItemsTotalPrice}")
      }
    }
  }
}