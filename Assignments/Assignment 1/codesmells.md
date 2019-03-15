# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# Template

Use the following Code Smell template (copy and paste it at the end of this file and then edit it; don't include the "Begin template" or "End template" lines):

==== Begin template ====
## Code Smell: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how this particular code smells.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]
==== End template ====

# List of code smells




## Code Smell: [Duplicate code]

### Code Smell Category: [Dispensables]

### List of classes and line numbers involved:

* [class PickerOrderList, lines: 9-105]
* [class SequencerOrderList, lines: 9-105]

### Description:

[The code in the above mentioned classes are nearly identical and duplicate code
makes the structure of the code complicated and hard to support.]

### Solution:

[Since the duplicate code is found in two different classes and the classes are not part of 
a hierarchy, then we use "Extract Superclass" in order to create a single superclass for these
classes that maintains all the previous functionality.]




## Code Smell: [Long Method]

### Code Smell Category: [Bloaters]

### List of classes and line numbers involved:

* [class WarehouseSimulation, lines: 41-96]

### Description:

[The above mentioned class has a method which contains 56 lines. The method is oversize and it 
becomes hard to understand and maintain.]

### Solution:

[We can use "Extract Method" to reduce the length of the method. If local variables and parameters
interfere with extracting a method, use "Replace Temp with Query", "Introduce Parameter Object" or 
"Preserve Whole Object". For conditionals, use "Decompose Conditional".]




## Code Smell: [Large Class]

### Code Smell Category: [Bloaters]

### List of classes and line numbers involved:

* [class WarehouseManager, lines: 13-202]

### Description:

[The class WarehouseManager contains many fields/methods/lines of code, which requires developers
to remember a large number of attributes for a class. It is also easy to make duplication of code
functionality.]

### Solution:

[We can split it up: if part of the behaviour of the large class can be spun off into a separate
component, then we can use "Extract Class". If part of the behavior of the large class can be 
implemented in different ways or is used in rare cases, then we can use "Extract Subclass".]




## Code Smell: [Speculative Generality]

### Code Smell Category: [Dispensables]

### List of classes and line numbers involved:

* [class OrderListManager, lines: 22-25]

### Description:

[The code in line 25 is created "just in case" to support anticipated future features that never get
implemented. As a result, code becomes hard to understand and support.]

### Solution:

[We can simply delete the unused fields to achieve slimmer code and easier support.]




## Code Smell: [Temporary Field]

### Code Smell Category: [Object-Orientation Abusers]

### List of classes and line numbers involved:

* [class WarehouseManager, lines: 52-55; 166-177]

### Description:

[The "sequenceOrderList" field gets its value (and thus needed by object) only under certain 
circumstance. Outside of the circumstance, it is empty or ignored.]

### Solution:

[We can convert the "sequenceOrderList" field to a local variable.]
