# An Anticheat Base

**An Anticheat Base** was made to reduce the amount of boilerplate when making an anticheat. With An Anticheat Base, anticheat creation will become *child's play*. AAB was built using **Kotlin**, so Kotlin users might benefit more from this, but it shouldn't be a problem for Java users. Powered by [*PacketEvents* by *retrooper*](https://github.com/retrooper/packetevents) and designed with performance in mind, AAB should easily deliver great performance results.


# Usage

## Main class

When using AAB, your Main class has to extend `AntiCheatPlugin`. Here is an example Main class.

Example Main class (Java):

![Example Main class (Java)](https://i.imgur.com/4qWK6Cd.png)

Example Main class (Kotlin):

![Example Main class (Kotlin)](https://i.imgur.com/EtjrpRb.png)

## Creating a check

Creating a check has never been easier! All you have to do is extend the `Check` class! There are currently 2 ways of checking (with more coming in the future).

 1. Packet Receive Event
 2. Packet Send Event

Example check (Java) :

![Example check (Java)](https://i.imgur.com/B73mANf.png)

Example check (Kotlin) :

![Example check (Kotlin)](https://i.imgur.com/BstOFwE.png)

## Creating a config

Have you ever wanted to be able to easily create configs and easily create configurable variables? Well AAB has you covered! You can quickly create a config in a couple of lines by extending `AntiCheatConfig`!

Example AntiCheatConfig (Java):

![Example AntiCheatConfig (Java)](https://i.imgur.com/5rLLvdw.png)

Example AntiCheatConfig (Kotlin):

![Example AntiCheatConfig (Kotlin)](https://i.imgur.com/dcrt4O5.png)

As you can see creating a config is very easy. But we aren't done yet, you will have to create a new instance of the class in your `onStartFinish` and then create the config with `createConfig()`. Please go back to the Main class example for more details.

Example ConfigValue (Kotlin):

![Example ConfigValue (Kotlin)](https://i.imgur.com/1fZJ0af.png)

## Creating a custom PlayerData/User class

AAB already comes with a `User` class containing a lot of useful stuff, but if you want to also have your own variables, you may create a custom PlayerData class extending the `User` class.

Example Custom PlayerData class (Java):

![Example Custom PlayerData class (Java)](https://i.imgur.com/r47uIgL.png)

Example Custom PlayerData class (Kotlin):

![Example Custom PlayerData class (Kotlin)](https://i.imgur.com/1Q4p4c4.png)


# Installation

The installation process is very easy, there are 2 ways of doing it.

## Using Lilliputian

**Lilliputian** makes shading libraries easier than ever, If you do not know what Lilliputian is, please visit: https://github.com/GoDead/Lilliputian

COMING SOON

## Cloning the AAB and including it in your project.

All you have to do is clone the repository and include the source code in your project.

## Shading with JitPack

Add An AntiCheat Base as dependency using Maven or Gradle, and shade it. https://jitpack.io/#GoDead/An_AntiCheat_Base


# Support

For support, please join the official support server

https://discord.gg/SHa7uD8

![Discord Server](https://discord.com/api/guilds/730339636639039548/widget.png?style=banner2)


# License

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**An AntiCheat Base** is licensed under the MIT License
