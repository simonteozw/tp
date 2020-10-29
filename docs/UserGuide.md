# TrackIt@NUS - User Guide

By: `Team W13-4` Since: `Aug 2020` License: `MIT`

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [About](#about)
    1. [Common Symbols](#common-symbols)
    2. [Command Format](#command-format)
    3. [Commands and their Purposes](#command-purpose)
4. [Layout](#layout)
    1. [Upcoming Tab](#upcomingtab)
    2. [Contacts Tab](#contactstab)
    3. [Module Tabs](#moduletabs)
5. [Features](#features)
    1. [Module](#module)
    2. [Lesson](#lesson)
    3. [Task](#task)
    4. [Contact](#contact)
    5. [Search](#search)
    6. [Help](#help)
    7. [Exit](#exit)
6. [FAQ](#faq)
7. [Command Summary](#command-summary)
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

1. Download the latest version of TrackIt@NUS from [here](https://github.com/AY2021S1-CS2103T-W13-4/tp/releases/tag/v1.2).

1. Copy the file to the folder you want to use as the home folder for TrackIt@NUS.

1. Double-click the file to start the app. The GUI shown below should appear in a few seconds.

![Ui](images/Ui.png)

1. You are now on the home page of TrackIt@NUS.

1. At the bottom of the screen, type your command in the Command Box (see [Features](#features) for more info) and
 press `Enter` on your keyboard to execute it.

## About <a name="about"></a>

There are many things you can use TrackIt@NUS for. We have structured this document so it is easy for you to find
 what you need. In the [Common Symbols](#common-symbols), [Command Format](#command-format), and 
 [Commands and their Purposes](#command-purpose) sections, you will find useful tips on reading this document. It is
  then followed by the [Features](#features) section, where the main features of TrackIt@NUS are documented.

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

:information_source: Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

### Commands and their Purposes <a name="command-purpose"></a>

| **Command** | ** Purpose** |
| -------- | --------- |
| **Add** | Add a module/lesson/task/contact to TrackIt@NUS. Relevant parameters must be present |
| **Edit** | Edit a module/lesson/task/contact. Relevant parameters must be present. All parameters that are not specified will **remain unchanged** |
| **Delete** | Delete a module/lesson/task/contact. Relevant parameters must be present |

--------------------------------------------------------------------------------------------------------------------
## Layout <a name="layout"></a>

This section gives you a brief overview of the layout of TrackIt@NUS.
TrackIt@NUS is divided into three main types of tabs:
* The **Upcoming** Tab,
* The **Contacts** Tab, and
* **Module** Tabs

When you switch to a tab, the tab text will be highlighted in **blue**.

### Upcoming Tab <a name="upcomingtab"></a>
This is the main tab of the application, and is the default page when the app is started. It features a calendar view of the days for the next week, as well as the **Tasks** and **Lessons** you have added under the respective days they fall under. 

1. The first section labelled **"Overdue"** in red text, shows the Tasks that are past their deadline, and you have yet to complete. These **Tasks** will remain there until you delete them.

2. The second section is the weekly calendar view, with your **Lessons** and **Tasks** shown under each day.

3. The last section is labelled **Future**, and shows any **Tasks** that you may have added with a deadline falling after the date 7 days from today.

![UpcomingPanelUi](images/UpcomingPanelUi.png)

### Contacts Tab <a name="contactstab"></a>
The contacts tab shows you all the **contacts** you have within the app. It will show you information for each contact such as:
* **NAME**: contact name

* **PHONE NUMBER**: contact's phone number

* **EMAIL**: contact's email

* **TAG**: any additional information you have about the contact, such as which module they are associated with (e.g. CS1010S), or their role (e.g. friend, TA)

### Module Tabs <a name="moduletabs"></a>
All the relevant information associated with each module is under the corresponding **Module** tab.

There are 3 sections within a Module tab:
1. **Lessons**: Lists out the weekly lessons you have for the module (i.e. lecture/tutorial/lab/sectionals)

2. **Tasks**: Lists out the pending tasks you have for the module.

3. **Contacts**: Lists out the contacts associated with the module. (e.g. friends, profs, TAs)

--------------------------------------------------------------------------------------------------------------------
## Features <a name="features"></a>

This section contains all the information you need to know about the features of TrackIt@NUS. To use each feature 
or sub-feature, you will need to enter the command into the Command Box.

## Module <a name="module"></a>

### Add

For: Add a new module.

Format: `M add m/MODULE_CODE n/NAME`

Examples: `M add m/CS1231S n/Discrete Structures`

### Edit
For: Edit the module with the specified `MODULE_CODE`.

Format: `M edit m/MODULE_CODE [m/NEW_MODULE_CODE] [n/NAME]`

Examples: `M edit m/CS1231S m/CS1101S n/Programming Methodology`

Remark: The `MODULE_CODE` provided must be present in the Module list. At least 1
 field must be provided for editing. i.e. `M edit m/CS2100` is not allowed.

### Delete
For: Delete the module with the specified `MODULE_CODE`.

Format: `M delete m/MODULE_CODE`

Examples: `M delete m/CS2100`

Remark: The `MODULE_CODE` provided must be present in the Module list.

## Lesson <a name="lesson"></a>

### Add
For: Add a lesson to a module.

Format: `L add m/MODULE_CODE n/TYPE d/DATE a/LOCATION`

Examples: `L add m/CS2103T n/tutorial d/2pm - 3pm Wed a/COM1`

Remark: The module must exist prior to adding a lesson. `DATE` must be of the form `START_TIME - END_TIME DAY_OF_WEEK`.`TYPE` must be one of
 the following:
* `LEC` (for lecture)
* `TUT` (for tutorial)
* `LAB` (for laboratory)
* `SEC` (for sectional teaching)
* `REC` (for recitation)

### Edit

For: Edit the detail of a lesson

Format: `L edit INDEX [m/MODULE_CODE] [n/TYPE] [d/DATE] [a/LOCATION]`

Examples: `L edit 1 m/CS2103T n/tutorial d/2pm - 3pm Monday`

Remark: The `INDEX` provided must be positive and cannot be larger than the number of Lessons. At least 1
 field must be provided for editing. i.e. `L edit 1` is not allowed.

### Delete
For: Delete the lesson with the specified `INDEX`

Format: `L delete INDEX`

Examples: `L delete 1`

Remark: The `INDEX` provided must be positive and cannot be larger than number of Lessons.

## Task <a name="task"></a>

Managing all your tasks can be so stressful! TrackIt@NUS helps alleviate that stress with our built-in Task Manager
! To view all your tasks, simply click on the upcoming tab as shown below.

![Ui](images/Upcoming.png)

This sorts all your tasks by date. To view module-specific tasks, click on any of the module tabs as shown below.

:information_source: All module-specific tasks are shown in the upcoming tab, but not vice versa.

You can also perform commands on the tasks as explained below.

### Add

After a day of school you realise that you now have a few more tasks to complete and wish to keep track of them. This is
 the command for adding a new task to TrackIt@NUS. 

Format: `T add n/NAME d/DATE [m/MODULE_CODE] [r/REMARK]`

Examples: 

* `T add n/Assignment 1 m/CS2100 d/20/11/2020 r/Focus on Chapters 1-3`
* `T add n/Plan for Bob's birthday d/12/11/2020`
* `T add n/Buy mooncakes for Mum d/28/10/2020 r/Remember to get Durian-flavoured ones`

:information_source: The parameters `m/MODULE_CODE` and `r/REMARK` are optional

:warning: The `DATE` provided must be in the format `dd/MM/yyyy`

### Edit
After adding a task to TrackIt@NUS, you may want to change it, perhaps extend the deadline or change it's remark. The
 edit command is what you should use to do so.

Format: `T edit INDEX [n/NAME] [d/DATE] [m/MODULE_CODE] [r/REMARK]`

Examples: 

![Ui](images/EditTask.png)

* `T edit 2 n/Work on midterm report` changes the name of the Task
* `T edit 2 r/min 1000 words` changes the remark of the Task
* `T edit 2 m/CS2100` changes the module code of the Task 

:information_source: The `INDEX` provided must be positive and cannot be larger than number of Tasks

:warning: At least 1 field must be provided for editing. i.e. `T edit 1` is not allowed

:bulb: Write `T edit 1 m/` to remove the module code from the task. Now, the task does not belong to any module

### Delete
Once you have completed a task, you can delete it. This is the command to do so.

Format: `T delete INDEX`

Examples: `T delete 2`

:information_source: The `INDEX` provided must be positive and cannot be larger than the number of Tasks

## Contact <a name="contact"></a>
Don't you hate it when you need help for one of your modules but you just can't remember who else is taking the module with you? 

Luckily for you, TrackIt@NUS allows you to tag your contacts with the modules you have in common, and lets you see those contacts all at once! Simply click on a module tab and you will find the list of contacts associated with that module, as you can see below.

![ModuleTabContacts](images/ModuleTabContacts.png)

To view all contacts from all modules, as well as any contacts that are not associated with any module, click on the **Contacts** tab in the side panel.

### Add
A new semester has started and you want to keep track of your new classmates. This is the command to add a new contact.

Format: `C add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]...`

Examples:

* `C add n/John Doe p/98765432 e/jon@jon.com t/CS2020`
* `C add n/Rajesh Kumar p/98762342 e/raj@law.com t/Friend`

### Edit
After adding a contact to TrackIt@NUS, maybe you realised you spelt their name wrong, or you want to update the contact fields. The edit command is what you should use to do so.

Format: `C edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]...`

Examples:
* `C edit 1 n/Johnny Depp` changes the name of the Contact.
* `C edit 2 n/Sam e/sam@gmail.com t/CS2100 t/TA` changes the name, email and tags of the Contact.

Remark: The `INDEX` provided must be positive and cannot be larger than the number of Contacts. At least 1
 field must be provided for editing. i.e. `C edit 1` is not allowed.

:warning: When editing tags, existing tags of contact will be removed (i.e. adding tags is not cumulative)

:bulb: Can remove all tags by typing t/ without any tags following it

### Delete
You have broken up with your girlfriend and want to forget their existence, starting by deleting their contact in TrackIt@NUS. This is the command to do so.

Format: `C delete INDEX`

Examples: `C delete 1`

Remark: The `INDEX` provided must be positive and cannot be larger than the number of Contacts.

## Searching the app <a name="search"></a>

For: Searching the app for relevant modules, lessons, tasks, or contacts

Format: `find KEYWORD`

Examples: `find CS1101S`

## Getting help <a name="help"></a>

For: Getting help from the program.

Format: `help`

Examples: `help`

## Exiting the app <a name="exit"></a>

For: Exit the program.

Format: `exit`

Examples: `exit`

## Saving the data

TrackIt@NUS data is saved in the hard disk automatically after any command that changes the data. There is no need to
 save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ <a name="faq"></a>

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
 the data of your previous TrackIt@NUS home folder.

## Command Summary <a name="command-summary"></a>

### Module <a name="module-commands"></a>

| Command | Example |
| ---- | ------ |
| **Add:** `M add m/MODULE_CODE n/NAME` | `M add m/CS2100 n/Computer Organisation` |
| **Edit:** `M edit m/MODULE_CODE [n/NAME]` | `M edit n/Discrete Mathematics` |
| **Delete:** `M delete m/MODULE_CODE` | `M delete m/CS1101S` |

### Task <a name="task-commands"></a>

| Command | Example |
| ---- | ------ |
| **Add:** `T add n/NAME d/DATE [m/MODULE_CODE] [r/REMARK]` | `T add n/Assignment 1 d/20/11/2020 r/Focus on Chapters 1-3` |
| **Edit:** `T edit INDEX [n/NAME] [d/DATE] [m/MODULE_CODE] [r/REMARK]` | `T edit 1 n/Finish Assignment` |
| **Delete:** `T delete INDEX` | `T delete 1` |

### Lesson <a name="lesson-commands"></a>

| Command | Example |
| ---- | ------ |
| **Add:** `L add m/MODULE_CODE n/TYPE d/DATE a/LOCATION` | `L add m/CS2103T n/tutorial d/2pm - 3pm Wed a/COM1` |
| **Edit:** `L edit INDEX [m/MODULE_CODE] [n/TYPE] [d/DATE] [a/LOCATION]` | `L edit 1 a/LT17` |
| **Delete:** `L delete INDEX` | `L delete 1` |

### Contact <a name="contact-commands"></a>

| Command | Example |
| ------ | ---------- |
| **Add:** `C add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]...` | `C add n/John Doe p/98765432 e/jon@jon.com t/Brother` |
| **Edit:** `C edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]...` | `C edit 1 n/Johnny Depp p/98999899` |
| **Delete:** `C delete INDEX` | `C delete 1` |

### General <a name="general-commands"></a>

| Command | Example |
| ---- | ------ |
| **Help:** `help` | `help` |
| **Exit:** `exit` | `exit` |
