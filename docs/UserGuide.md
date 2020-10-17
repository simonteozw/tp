# TrackIt@NUS - User Guide

By: `Team W13-4` Since: `Aug 2020` License: `MIT`

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [About](#about)
    1. [Common Symbols](#common-symbols)
    2. [Command Format](#command-format)
4. [Features](#features)


--------------------------------------------------------------------------------------------------------------------

## Introduction <a name="introduction"></a>

Welcome to the **TrackIt@NUS** user-guide!

Do you want to balance schoolwork and having a social life, but dislike having to use 3 different
 apps to keep track of everything? Then look no further! TrackIt@NUS is a one-stop solution for busy students like you
  and I. 
  
TrackIt@NUS is a desktop app for managing modules, lessons, tasks, and contacts, tailored to the needs of NUS students and
  optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User
   Interface (GUI)**. If you can type fast, you will be able to manage your academic and social commitments much faster
    than by using other traditional GUI apps.
    
What are you waiting for? Head on to [Section 2, **Quick Start**](#quick-start) to learn more!

## Quick start <a name="quick-start"></a>

1. Ensure you have **Java 11** or above on your Computer.

1. Download the latest version of **TrackIt@NUS** from [here](https://github.com/AY2021S1-CS2103T-W13-4/tp/releases/tag/v1.2).

1. Copy the file to the folder you want to use as the home folder for **TrackIt@NUS**.

1. Double-click the file to start the app. The GUI shown below should appear in a few seconds. 

![Ui](images/Ui.png)

1. You are now on the home page of **TrackIt@NUS**.

1. At the bottom of the screen, type your command in the Command Box (see [Features](#features) for more info) and
 press `Enter` on your keyboard to execute it.

## About <a name="about"></a>

### Common Symbols <a name="common-symbols"></a>

| Symbol | Description |
| --- | ----------- |
| :information_source: | Something important to take note of |
| :bulb: | A tip is being mentioned |
| :warning: | Something to be careful of |

### Command Format <a name="command-format"></a>

| **Format** | **Meaning** | **Example** |
| ----- | -------- | -------------- |
| `lower_case/` | These are **prefixes** and are used to separate the different parameters of a command | `n/`, `d/`, `t/` |
| `UPPER_CASE` | These are **parameters** and need to be supplied to complete certain commands | `C add n/John ...` |
| `[UPPER_CASE]` |  These are **optional parameters** | `C add n/John ...` or `C add n/John t/Friend ...` both work |
| `…`​ | These are parameters that can be used **multiple times** or **none at all** | `C add n/John t/Brother-in-law t/Colleague t/Friend ...` |

Parameters can be in any order.<br>
  e.g. if the command specifies `n/{name} p/{phone_number}`, `p/{phone_number} n/{name}` is also acceptable.

--------------------------------------------------------------------------------------------------------------------

## Features <a name="features"></a>

## Module

### View
For: Shows the information about a module.

Format: `M info m/{module_code}`

Examples: `M info m/CS1231S`

### Add

For: Add a new module.

Format: `M add m/{module_code} n/{name} d/{description}`
Examples: `M add m/CS1231S n/Discrete Structures d/Introductory mathematical tools required for computer science`

### Edit
For: Edit a module.

Format: `M edit `

Examples: `M edit m/CS1231S  n/Discrete Structures d/Introductory mathematical tools`

### Delete
For: Delete a module

Format: `M delete m/{module_code}`

Examples: `M delete m/CS1231S`

## Task

### View
Description: Shows the information about a task.

Format: `T info n/{name}`

Examples: `T info n/CS2100 midterm`

### Add
Description: Adds a task to TrackIt@NUS

Format: `T add n/{task name} d/{task date} a/{location} w/{weightage} r/{remark}`

Examples: `T add n/Assignment 1 due d/20/11/2020 a/NUS w/20 r/Focus on Chapters 1-3`

### Edit
Description: Edits the detail of a task.

Format: `T edit {event_index} n/{event name} d/{event date}`

Examples: `T edit 1 n/Assignment 1 due t/22/11/2020`

### Delete
Description: Deletes a task from the list.

Format: `T delete {event_index}`

Examples: `T delete 2`

### List
Description: Lists all events.

Format: `T list`

Examples: `T list`

## Lesson

### View
For: Shows the information about a lesson

Format: `L info m/{module_code} t/{type}`

Examples:
* `L info m/CS2103T t/tutorial`
* `L info m/CS2103T t/lesson`

### Add
For: Add a lesson to a module. The module needs to be existing prior to adding.

Format: `L add m/{module_code} n/{type} t/{time} v/{venue or Zoom link}`

`{time}` : the time of the first lesson of that type in this semester

Examples: `L add m/CS2103T n/tutorial t/01/08/2019 4pm-6pm v/COM1`

### Edit

For: Edit the detail of a lesson

Format: `L edit m/{module_code} [n/{type}] [t/{time}] [v/{venue or Zoom link}]`

Examples: `L edit m/CS2103T n/tutorial t/02/08/2019 4pm-6pm v/COM1`

### Delete
For: Delete all lessons of that type

Format: `L delete m/{module_code} n/{type}`

Examples: `L delete m/CS2103T n/tutorial`


### List
For: List all lessons (of all module or of a single module)

Format: `L list m/{module_code}`

Examples: `L list m/CS2103T`

## Contact

### View

For: Shows the specific details of the specified contact.

Format: `C info n/{name}`

### Add

For: Adds the contact to TrackIt@NUS.

Format: `C add n/{name} p/{phone_number} e/{email} a/{address} [t/{tag}]...`

Examples:

* `C add n/John Doe p/98765432 e/jon@jon.com a/Sesame Street t/Brother`
* `C add n/Rajesh Kumar p/98762342 e/raj@law.com a/UOB Tower t/criminal lawyer`

### Edit

For: Edits the contact with the specified index.
* Index must be positive and must be within range of number of contacts in listing
* At least 1 of the optional arguments show above must be present
* Arguments not present will remain unchanged
* When editing tags, existing tags of contact will be removed (i.e. adding tags is not cumulative
* Can remove all tags by typing t/ without any tags following it

Format: `C edit {index} [n/{name}] [p/{phone_number}] [e/{email}] [a/{address}] [t/{tag}]s...`

Examples:
* `C edit 1 n/Johnny Depp p/98999899`
* `C edit 2 n/Batman e/batman@justiceleague.com`

### Delete

For: Deletes the contact with the specified index.

Format: `C delete {index}} `

Examples: `C delete 1 `

### List
For: Lists out all contacts.

Format: `C list`

## Clearing all contacts : `clear`

Clears all contacts from the address book.

Format: `clear`

## Exiting the program : `exit`

Exits the program.

Format: `exit`

## Saving the data

TrackIt@NUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.
