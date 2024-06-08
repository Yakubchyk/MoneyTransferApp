TransferApp
TransferApp is a Java application designed to facilitate monetary transfers from one account to another. 
The application reads account and transfer data from text files, processes the transfers, updates account balances,
and generates a report of the completed operations.

Project Structure
The project is organized into several packages, each responsible for specific functionality:

model: Contains classes for representing data models.

Account: Represents a bank account with an account number and balance.
Transfer: Represents a transfer from one account to another with a specified amount.
exception: Contains classes for handling exceptions.

FileProcessingException: Thrown when there are errors in file processing.
InvalidTransferException: Thrown when transfer data is invalid.
service: Contains classes implementing the main logic of the application.

FileParserService: Responsible for reading and processing files, performing transfers, and generating reports.

Running the Application
Upon running the application, the user is prompted to select an action:

Invoke the file parsing operation to process transfer files from the input directory.
Invoke the operation to output the list of all transfers from the report file.

Example Workflow.
The application reads transfer files from the input directory.
Each file is processed line by line, and transfers are performed between accounts.
Updated account data is saved to the accounts.txt file.
A report of processed transfers is created and saved to the report.txt file.
Processed files are moved to the archive directory.

In summary, TransferApp automates the process of monetary transfers, 
ensures the integrity of account data, and generates reports of completed operations.



![MoneyTransferApp](https://github.com/Yakubchyk/MoneyTransferApp/assets/135871084/8b4acd0e-82f1-4d94-a795-83d640af95ba)
