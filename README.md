# 19300 Final Project -
# Personal Expense Tracker
A simple ledger program.

## Project Goal
The goal of this project is to create a Java program that helps people track their spending habits. Users can record expenses, organize purchases, and calculate their totals.

## MVP Scope
The first version of the program will include:
-Ability to add, edit, delete expenses
-Ability to categorize expenses
-Ability to calculate & display totals
-Ability to save expenses to a file and open the same info later.

## Future Plans (V2.0+)
Features I would like to add later but probably won't:
-Monthly spending summaries
-Monthly budgets that are automatically calculated
-Trend data that is calculated based off of your habits over time

## Tech Stack
Language: Java
Input: java.util.Scanner
Data structures: java.util.ArrayList
File storage: Java I/O (java.io)
Date management: java.time.LocalDate
Version control: Git and GitHub
Development environment: Visual Studio Code

## How It'll Work
-Program starts and checks to load previously saved expenses.
-User makes a choice in a command-line menu.
-User can add, view, or delete expenses.
-Program calculates total spending when requested.
-Expenses are saved to a CSV file when the program exits.

## Chunked Build Plan
1. Set up GitHub repository and Java project with a proper README file.
2. Create an Expense class with defined attributes, accessor/mutator methods, and a constructor.
3. Build a command-line menu.
4. Implement adding/viewing expenses.
5. Implement expense categories and deletion.
6. Implement calculations of different totals.
7. Add file storage and test the fully completed program.
