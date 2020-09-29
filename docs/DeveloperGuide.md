---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* take multiple modules
* want to easily track their weekly calendar
* want to be notified about important academic dates coming up
* want to have their personal todos & events integrated with their academic calendar
* want to access related contacts when they browse a module
* want to keep track of modules' details (grading scheme, module's knowledge coverage)
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**
* able to integrate personal todos/events into the weekly academic calendar. 
* able to manage modules' information (module's knowledge coverage, grading scheme) & related contacts in one app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                   | So that I can…​                                                        |
| -------- | -------------------- | ---------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | student              | have all modules' important dates in one place                         | never miss any event                                                   |
| `* * *`  | student              | have information (name, description, time) about any important dates   | always know what to prepare                                            |
| `* * *`  | student              | have all the tutorial/lab details (Zoom link, time) in one place       | easily find them when I need it                                        |
| `* * *`  | forgetful student    | find the grading structure of a module                                 | better revise for each assignment / mid-term / exam                    |
| `* *`    | student              | have all the module descriptions in one place                          | read them if I need to                                                 |
| `*`      | struggling student   | access the contact information of my TA/Prof                           | ask question or schedule a consultation                                |

### Use cases

(For all use cases below, the **System** is the `TrackIt@NUS` application and the **Actor** is the `user`, unless specified otherwise)

**Use case: Find the grading structure of a module**

**MSS**

1.  User requests to list the modules
2.  TrackIt@NUS shows a list of all modules
3.  User requests to find the grading structure of a module in the list
4.  TrackIt@NUS shows the requested grading structure

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index (or module name) is invalid.

    * 3a1. TrackIt@NUS shows an error message.

      Use case resumes at step 2.
      

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**
