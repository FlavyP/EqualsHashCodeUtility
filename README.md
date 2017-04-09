# EqualsHashCodeUtility

Hey! This is a utility that will help you create your **equals** and **hashCode** methods using the Apache Commons [EqualsBuilder](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/EqualsBuilder.html) and [HashCodeBuilder](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html). I still have to add some bits, but I decided to publish it here anyways.

It's using the *Reflections API* to get all the field of a class and determines if the field should be added to the **equals** and **hashCode** methods. I guess, in theory, all fields of a class from the *model* should be primitives/enums/other model classes, but I have encountered situations where other type of fields(i.e. *resolvers*) made their way into a model class.

Currently I am using a **@Test** class/method to build the **equals** method, which is just printed to the console. I haven't found a way to make Java add a method to a Java class. Probably the solution here is to make this into a plugin?

I'm open to suggestions and please bear with me because this is not the final version of the code :smiley:. 
