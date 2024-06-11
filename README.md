# TransferApp

**TransferApp** is a Java application designed to facilitate monetary transfers from one account to another. The application reads account and transfer data from text files, processes the transfers, updates account balances, and generates a report of the completed operations.

## Functionality

### Parse Files

1. Place your transfer files in the `src/com/main/input` directory.
2. Run the application and choose the option to parse files.
3. The application will read the transfer files, process the transfers, update account balances, and generate a report.

### View Report by Date Range

1. Run the application and choose the option to view the report by date range.
2. Enter the start and end dates in the format `dd-MM-yyyy HH:mm:ss`.
3. The application will display the report entries within the specified date range.

## Example Workflow


1. The application reads transfer files from the `input` directory.
2. Each file is processed line by line, and transfers are performed between accounts.
3. Updated account data is saved to the `accounts.txt` file.
4. A report of processed transfers is created and saved to the `report.txt` file.
5. Processed files are moved to the `archive` directory.

## Contributing

Contributions are welcome! Please create a pull request or submit an issue for any improvements or suggestions.

## License

This project is licensed under the MIT License.



![MoneyTransferApp_1](https://github.com/Yakubchyk/MoneyTransferApp/assets/135871084/a07f588f-434c-47b9-9798-2e607738d4a2)
