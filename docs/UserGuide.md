# TrackIt@NUS - User Guide

By: `Team W13-4` Since: `Aug 2020` License: `MIT`

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [About](#about)
    1. [Common Symbols](#common-symbols)
    2. [Command Format](#command-format)
4. [Features](#features)
    1. [Module](#module)
    2. [Task](#task)
    3. [Lesson](#lesson)
    4. [Contact](#contact)
5. [FAQ](#faq)
6. [Command Summary](#command-summary)
    1. [Module Commands](#module-commands)
    2. [Task Commands](#task-commands)
    3. [Lesson Commands](#lesson-commands)
    4. [Contact Commands](#contact-commands)
    5. [General Commands](#general-commands)


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

## Module <a name="module"></a>

### View
For: Show the information about a module.

Format: `M info INDEX`

Examples: `M info 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Module list.


### Add

For: Add a new module.

Format: `M add m/MODULE_CODE n/NAME d/DESCRIPTION`

Examples: `M add m/CS1231S n/Discrete Structures d/Introductory mathematical tools required for Computer Science`

### Edit
For: Edit the specified module.

Format: `M edit INDEX [m/MODULE_CODE] [n/NAME] [d/DESCRIPTION]`

Examples: `M edit 1 m/CS1231S n/Discrete Mathematics d/Introductory mathematical tools`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Module list. At least 1
 field must be provided for editing. i.e. `M edit 1` is not allowed.

### Delete
For: Delete a module.

Format: `M delete INDEX`

Examples: `M delete 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Module list.

## Task <a name="task"></a>

### View
Description: Show the information about a task.

Format: `T info INDEX`

Examples: `T info 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Module list.

### Add
Description: Add a new task.

Format: `T add n/NAME d/DATE a/LOCATION w/WEIGHTAGE r/REMARK`

Examples: `T add n/Assignment 1 d/20/11/2020 a/NUS w/20 r/Focus on Chapters 1-3`

Remark: The `DATE` provided must be in the form `dd/MM/yyyy`. The `WEIGHTAGE` provided must be a number.

### Edit
Description: Edit the specified task.

Format: `T edit INDEX [n/NAME] [d/DATE] [a/LOCATION] [w/WEIGHTAGE] [r/REMARK]`

Examples: `T edit 1 n/Assignment 1 s/22/11/2020`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Task list. At least 1
 field must be provided for editing. i.e. `T edit 1` is not allowed.

### Delete
Description: Deletes a task.

Format: `T delete INDEX`

Examples: `T delete 2`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Module list.

### List
Description: Lists all tasks.

Format: `T list`

Examples: `T list`

## Lesson <a name="lesson"></a>

### View
For: Shows the information about a lesson

Format: `L info m/MODULE_CODE n/TYPE`

Examples:
* `L info INDEX`
* `L info 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Lesson list.

### Add
For: Add a lesson to a module.

Format: `L add m/MODULE_CODE n/TYPE d/DATE a/LOCATION w/WEIGHTAGE`

Examples: `L add m/CS2103T n/tutorial t/01/08/2019 d/11/12/2020 a/COM1 w/25`

Remark: The module needs to be existing prior to adding. `TYPE` must be one of the following:
* `lecture`
* `tutorial`
* `lab`
* `sectional`

### Edit

For: Edit the detail of a lesson

Format: `L edit INDEX [m/MODULE_CODE] [n/TYPE] [d/DATE] [a/LOCATION] [w/WEIGHTAGE]`

Examples: `L edit 1 m/CS2103T n/tutorial t/02/08/2019`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Lesson list. At least 1
 field must be provided for editing. i.e. `L edit 1` is not allowed.

### Delete
For: Delete all lessons of that type

Format: `L delete INDEX`

Examples: `L delete 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Lesson list.

### List
For: List all lessons (of all module or of a single module)

Format: `L list [m/MODULE_CODE]`

Examples: `L list m/CS2103T` or `L list`

## Contact <a name="contact"></a>

### Add

For: Adds the contact to TrackIt@NUS.

Format: `C add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`

Examples:

* `C add n/John Doe p/98765432 e/jon@jon.com a/Sesame Street t/Brother`
* `C add n/Rajesh Kumar p/98762342 e/raj@law.com a/UOB Tower t/Friend`

### Edit

For: Edits the specified contact.

Format: `C edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

Examples:
* `C edit 1 n/Johnny Depp p/98999899`
* `C edit 2 n/Batman e/batman@justiceleague.com t/Superhero`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Contact list. At least 1
 field must be provided for editing. i.e. `L edit 1` is not allowed.
 
:warning: When editing tags, existing tags of contact will be removed (i.e. adding tags is not cumulative)

:bulb: Can remove all tags by typing t/ without any tags following it

### Delete

For: Deletes the contact with the specified index.

Format: `C delete INDEX`

Examples: `C delete 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the length of the Contact list.

### List
For: Lists out all contacts.

Format: `C list`

Example: `C list`

### Clear

For: Delete all contacts.

Format: `C clear`

Examples: `C clear`

## Getting help

For: Getting help from the program.

Format: `help`

Examples: `help`

## Exiting the program

For: Exit the program.

Format: `exit`

Examples: `exit`

## Saving the data

TrackIt@NUS data is saved in the hard disk automatically after any command that changes the data. There is no need to
 save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ <a name="faq"></a>

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

## Command Summary <a name="command-summary"></a>

### Module <a name="module-commands"></a>

| Command | Example |
| ---- | ------ |
| **View:** `M info INDEX` | `M info 1` |
| **Add:** `M add m/MODULE_CODE n/NAME d/DESCRIPTION` | `M add m/CS2100 n/Computer Organisation d/Learning about hardware` |
| **Edit:** `M edit INDEX [m/MODULE_CODE] [n/NAME] [d/DESCRIPTION]` | `M edit 1 n/Discrete Mathematics` |
| **Delete:** `M delete INDEX` | `M delete 1` |

### Task <a name="task-commands"></a>

| Command | Example |
| ---- | ------ |
| **View:** `T info INDEX` | `T info 1` |
| **Add:** `T add n/NAME d/DATE a/LOCATION w/WEIGHTAGE r/REMARK` | `T add n/Assignment 1 d/20/11/2020 a/NUS w/20 r/Focus on Chapters 1-3` |
| **Edit:** `T edit [n/NAME] [d/DATE] [a/LOCATION] [w/WEIGHTAGE] [r/REMARK]` | `T edit 1 n/Finish Assignment` |
| **Delete:** `T delete INDEX` | `T delete 1` |
| **List:** `T list` | `T list` |

### Lesson <a name="lesson-commands"></a>

| Command | Example |
| ---- | ------ |
| **View:** `L info INDEX` | `L info 1` |
| **Add:** `L add m/MODULE_CODE n/TYPE d/DATE a/LOCATION w/WEIGHTAGE` | `L add m/CS2103T n/tutorial t/01/08/2019 d/11/12/2020 a/COM1 w/25` |
| **Edit:** `L edit [m/MODULE_CODE] [n/TYPE] [d/DATE] [a/LOCATION] [w/WEIGHTAGE]` | `L edit 1 d/12/12/2020` |
| **Delete:** `L delete INDEX` | `L delete 1` |
| **List:** `L list [m/MODULE_CODE]` | `L list` |

### Contact <a name="contact-commands"></a>

| Command | Example |
| ------ | ---------- |
| **Add:** `C add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...` | `C add n/John Doe p/98765432 e/jon@jon.com a/Sesame Street t/Brother` |
| **Edit:** `C edit INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...` | `C edit 1 n/Johnny Depp p/98999899` |
| **Delete:** `C delete INDEX` | `C delete 1` |
| **List:** `C list` | `C list` |
| **Clear:** `C clear` | `C clear` |

### General <a name="general-commands"></a>

| Command | Example |
| ---- | ------ |
| **Help:** `help` | `help` |
| **Exit:** `exit` | `exit` |
