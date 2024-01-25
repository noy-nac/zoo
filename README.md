
# Zoo Project

## Why Use Abstract Classes?

Welcome to the Land of the Wild (a zoo). In this zoo, there are two kinds of inhabitants, **animals** and **food**. Animals, like the humble `Dog`, the unhumble `Cat`, and the friendly neighborhood `Rat`, are capable of two actions: `eat` and `move`. Additionally, animals have a intrinsic desire to seekout food, called `hunger`, and they can also get sick! Food, on the other hand, can really only do one thing: `beEaten`.

As you may have guessed, we will use inheritance to add structure to our Zoo program. For example, a `Cat` is an `Animal` so `Cat` will extend `Animal`. Then any methods we define in `Animal` can be used in `Cat`.

There's just one problem. We all agree animals can `eat`, so we will add that method to the `Animal` class for `Cat` to inherit. But what does an *animal* eat? Not a cat or a dog, but an *animal* in the abstract. Every animal eats different things. It's not clear how we should implement `eat` in the parent class even though surely every animal *can* `eat`.

```java
Animal a = new Animal();
a.eat(); // its not clear what this should do
```

The solution is abstract classes. Of course every animal can eat, but the implementation details of how the animal eats depends on the specific animal. In this case we say that the method `eat` is ***abstract*** (this is a Java keyword). We can defer implementation of an abstract method for the child class to decide.

Furthermore, since the concrete child class actually inherits the method from its abstract parent class, the child class MUST implement it (otherwise our code will not compile). Therefore, at runtime our child class is **guranteed** to have behavior associated with the inherited method, unlike the parent class, which by definition has no behavior associated with the abstract method.

Any class that has at least one abstract method, must itself be abstract. ***It isn't possible to directly instanciate an abstract class*** because, going back to the example above, it would be unclear what to do if we were to call the abstract method inside the class.

Legal:
```java
Animal cat = new Cat();
cat.eat();
```

Also legal:
```java
Cat cat = new Cat();
cat.eat();
```

Illegal:
```java
Animal cat = new Animal(); 
cat.eat(); // undefined behavior
```

Here's what our abstract class Animal might look like. Note that our final implementation will actually have more complexity than this example.

```java
public abstract class Animal {
    protected int hunger;
    protected boolean isSick;

    public Animal() {
        this.hunger = 0;
        this.isSick = false;
    }

    // abstract methods have a ";" instead of "{ }" with code
    public abstract void eat(); 
    public abstract void move();
}
```

##

![image](./abstract_heiarchy.png)

## Required Classes
- Entity (abstract)
- Animal (abstract)
- Food (abstract)

## 



## Animal

### Cat
 - A cat has 9 lives

`abstract class ZooEntity`

`abstract class Animal`




