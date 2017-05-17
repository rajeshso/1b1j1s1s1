package com.bjss.basket

/**
  * Created by Rajesh on 30-Apr-17.
  */
import com.bjss.UnitSpec
import com.bjss.items.{Apple, Bread, Milk, Soup}
import com.bjss.offer.{DisableOffer, EnableOffer}

class ShoppingBasketTest extends UnitSpec {
  "MyShoppingBasket" should "add apples and Milk" in {
    val myBasket = new ShoppingBasket()
    myBasket += Apple
    myBasket += Apple
    myBasket += Milk
    myBasket += Apple
  }

  "MyShoppingBasket" should "record the number of apples as two" in {
    val myBasket = new ShoppingBasket()
    myBasket += Apple
    myBasket += Apple
    myBasket.itemCount should be(2)
  }

  "MyShoppingBasket" should "record the number of apples and Milk as a sum total of 3" in {
    val myBasket = new ShoppingBasket()
    myBasket += Apple
    myBasket += Apple
    myBasket += Milk
    myBasket.itemCount should be(3)
  }
  "MyShoppingBasket" should "add an apple and tell the price as 1.0 when there is no offer" in {
    val myBasket = new ShoppingBasket(DisableOffer)
    myBasket += Apple
    myBasket.basketItemsTotalPrice should be(1.0)
  }

  "MyShoppingBasket" should "add an apple and an milk and tell the price as 2.3 when there is no offer" in {
    val myBasket = new ShoppingBasket(DisableOffer)
    myBasket += Apple
    myBasket += Milk
    myBasket.basketItemsTotalPrice should be(2.3)
  }

  "MyShoppingBasket" should "add two apples and an orange and tell the price as 1.45 and the total items should be 3 when there is no offer" in {
    val myBasket = new ShoppingBasket(DisableOffer)
    myBasket += Apple
    myBasket += Milk
    myBasket += Apple
    myBasket.basketItemsTotalPrice should be(3.3)
    myBasket.itemCount should be(3)
  }
  "MyShoppingBasket" should "accept one apples but the price should be 0.9" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Apple
    basket.basketItemsTotalPrice should be(0.9)
  }

  "MyShoppingBasket" should "accept two apples but the price should be 1.8" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Apple
    basket += Apple
    basket.basketItemsTotalPrice should be(1.8)
  }
  "MyShoppingBasket" should "accept two apples and two soups but the discount is only for apples" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Apple
    basket += Apple
    basket += Soup
    basket += Soup
    basket.basketItemsTotalPrice should be(3.1)
    basket.itemCount should be(4)
  }
  "MyShoppingBasket" should "accept two apples, two soups and a milk but the discount is only for apples" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Apple
    basket += Apple
    basket += Soup
    basket += Soup
    basket += Milk
    basket.basketItemsTotalPrice should be(4.4)
    basket.itemCount should be(5)
  }
  "MyShoppingBasket" should "accept a bread and the price shoud be 0.8" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Bread
    basket.basketItemsTotalPrice should be(0.8)
    basket.itemCount should be(1)
  }
  "MyShoppingBasket" should "accept a soup and a bread and the price shoud be 1.45" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Soup
    basket += Bread
    basket.basketItemsTotalPrice should be(1.45)
    basket.itemCount should be(2)
  }
  "MyShoppingBasket" should "accept a soup and two breads and the price shoud be 2.25" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Soup
    basket += Bread
    basket += Bread
    basket.basketItemsTotalPrice should be(2.25)
    basket.itemCount should be(3)
  }
  "MyShoppingBasket" should "accept two soups and one breads and the price shoud be 0.65+0.65+(0.8/2)=1.7" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Soup
    basket += Soup
    basket += Bread
    basket.basketItemsTotalPrice should be(1.7)
    basket.itemCount should be(3)
  }
  "MyShoppingBasket" should "accept four soups and two breads and the price shoud be (0.65*4)+(0.8*2/2)=2.1" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Bread
    basket += Bread
    basket.basketItemsTotalPrice should be(3.4)
    basket.itemCount should be(6)
  }
  "MyShoppingBasket" should "accept five soups and two breads and the price shoud be (0.65*5)+(0.8*2/2)=4.05" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Bread
    basket += Bread
    basket.basketItemsTotalPrice should be(4.05)
    basket.itemCount should be(7)
  }
  "MyShoppingBasket" should "accept five soups and two breads and the price shoud be (0.65*5)+(0.8*2/2)+(0.8*1)=4.85" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Soup
    basket += Bread
    basket += Bread
    basket += Bread
    basket.basketItemsTotalPrice should be(4.85)
    basket.itemCount should be(8)
  }
  "MyShoppingBasket" should "accept an Apple, a Milk and a Bread and the price shoud be 3.0" in {
    val basket = new ShoppingBasket(EnableOffer)
    basket += Apple
    basket += Milk
    basket += Bread
    basket.basketItemsTotalPrice should be(3.0)
    basket.itemCount should be(3)
  }
  "MyShoppingBasket" should "accept an Apple, a Milk and a Bread and the price shoud be 3.1 if the offer is not enabled" in {
    val basket = new ShoppingBasket(DisableOffer)
    basket += Apple
    basket += Milk
    basket += Bread
    basket.basketItemsTotalPrice should be(3.10)
    basket.itemCount should be(3)
  }
}