# Wapp --> Wix

## Before running

Within the WappalyzerCSV, delete columns:

- title (X)
- description (Y)
- copyright (Z)
- schema.org types (AC)
- about (AW)
- locations (AX)
- company size(AY)

This stops commas within the values of these fields from affecting the array column count.

#### Note: Author recommends TableTool from the Mac App Store for manipulating the input CSV; Apple's Numbers app forces a file extension change when modifications are made.

## To run 

within `MakeWixCSVFromWappalyzerCSV.java`, modify `WAPP_CSV_PATH` and `OUTPUT_CSV_PATH` to desired values; ensure input CSV is stored directly under `files/`

Navigate to the root directory of the project and run:

```
cd code
javac MakeWixCSVFromWappalyzerCSV.java
java MakeWixCSVFromWappalyzerCSV
```

- Success: "CSV file was created successfully!"
- Failure: Error message.  Contact author for troubleshooting steps

If successful, view results in `files/[YOUR_OUTPUT_CSV_PATH]]`