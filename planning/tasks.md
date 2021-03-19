# Tasks

## User Story 1: As a user, I sometimes put the pages in the incorrect order. Could you give me a way to re-order the "pages" in a PDF so they make sense?

### Tasks

#### @anchor T1_1 1.1

Show all the pages in a navigation bar with page number.(3 points)

#### @anchor T1_2 1.2

Drag and drop [event] feature to reorder the desired page. (2 points)

#### @anchor T1_3 1.3

Reorder the PDF (1 point)

## User Story 2: As a reader, I want to be able to have a jump to any "page" of the pdf to make it easier to read over time and so I don't have to spend 4-5 minutes scrolling, OR a bookmark feature to click on to jump back to.

### Tasks

#### @anchor T2_1 2.1 

Implement a text field that allows the user to quickly jump to any "page" of the pdf that is requested. (2 pt)

#### @anchor T2_2 2.2

Implement a button that when clicked bookmarks the page currently on and added it to a list that can later be accessed. (2 pt)

## User Story 3: As a user, having a displayable word and character count when dealing with PDFs with raw text would be beneficial for adhering to document guidelines.

### Tasks

#### @anchor T3_1 3.1

Implement a displayable text box into the PDF editor GUI that displays a counter. (1 pt)

#### @anchor T3_2 3.2

Using a reader, scan the document for raw text and increment a counter based on either individual characters or whole words. (2 pt)

## User Story 4: As a user, I would like to be able to merge multiple PDF files into one so that I can easily consolidate information across multiple files into one document.

### Tasks

#### @anchor T4_1 4.1

The merge functionality needs to be implemented. This will require creating a new PDF that is made up of two or more separate PDF files. (2 points)

## User Story 5: As a user, I want an easy way to be able to select multiple occurrences of the same word so that I can edit  them all at once.

### Tasks

#### @anchor T5_1 5.1

Implement a text field that scans the document for all occurrences of a word and highlights them. (1 pt)

#### @anchor T5_2 5.2

Implement a text field that replaces all instances of a word that has been discovered previously. (1 pt)

## User Story 6: As a user, I want to be able to highlight text in a PDF document to make it easier to refer back to important information.

### Tasks

#### @anchor T6_1 6.1

The coloring functionality needs to be implemented. Once implemented, the user will be able to color text. (1 point)

## User Story 7: As a contractor/consultant, adding an esign/everify to a pdf document whenever seems very useful.

### Tasks

#### @anchor T7_1 7.1

Select what digital signature type to implement. Once implemented determine whether to find a signature library / build a library to use the EdDSA scheme. 

#### @anchor T7_2 7.2

A button for users to add their digital signature to sign documents. Once implemented, users would get to save their digital signature on documents. 

#### @anchor T7_3 7.3 

A button to add signature and initialize areas. Once implemented users can add signature areas and initialize markup. 

#### @anchor T7_4 7.4 

Implment addon to verify a document was signed. Once implemented users get notified that a document was signed on opening a document. 

## User Story 8: As a UI Designer I want to create and export mock UI/UX designs, so that I can create sharable designs in a common format like .pdf

### Tasks

#### @anchor T8_1 8.1

add ability to create new pdf document (1 point)

#### @anchor T8_2 8.2

add ability to specify dimensions of document pages (1 point)

#### @anchor T8_3 8.3

add ability to import external assets into the program (3 points)

#### @anchor T8_4 8.4

add ability to place imported assets onto document (3 points)

#### @anchor T8_5 8.5

add ability to draw geometric shapes on document (3 points)

#### @anchor T8_6 8.6

add ability to move assets around within document (3 points)

#### @anchor T8_7 8.7

add ability to add text boxes to document (3 points)

#### @anchor T8_8 8.8

add ability to export pdf document (1 point)

## User Story 9: As a Student I want to be able to directly draw on and annotate pdfs, this will allow me to complete assignments easily, or take notes more efficiently.


### Tasks

#### @anchor T9_1 9.1

Create a bitmap drawing tool - 8 points

#### @anchor T9_2 9.2

ensure drawing can be erased without affecting whatever might be underneath - 3 points

## User Story 10: As an editor, an undo feature implemented through a toolbar button with unlimited undos would allow me to fix mistakes much more easily.

### Tasks

#### @anchor T10_1 10.1

Implement a button into the PDF editor GUI with an indicator that it is an undo button via text label or an "undo arrow" image. (1 pt)

#### @anchor T10_2 10.2

Create a data structure to keep track of the individual actions taken by the user to edit the PDF. (3 pt)

#### @anchor T10_3 10.3

When the undo button is pressed a move is retrieved from the structure and the move is reversed from the PDF. (2 pt)

## User Story 11: As a student, I want to be able to extract code from the PDF so that I can refer later to the required specific notes, i.e. code, without wandering in the whole PDF.

### Tasks

#### @anchor T11_1 11.1

Identify the code from the given page in the PDF.(2 points)

#### @anchor T11_2 11.2

Extract the identified code.(1 point)

#### @anchor T11_3 11.3

Provide option to select the existing file for appending the code or create a new file.(1 point)

## User Story 12: As a user, I want to convert PDF document to/from various file formats (docx, xlsx, pptx, jpg) without changing the alignment so that, I can manipulate it as I wish.

### Tasks

#### @anchor T12_1 12.1:

   Add file extension adaptability into the save window for various file types - 1 point

#### @anchor T12_2_0 12.2.0: 

   be able to read non-editable/handwritten text into typed text that is editable - 5 points

#### @anchor T12_3_0 12.3.0: 

Save Pdf "pages" as images that are viewable - 3 points

#### @anchor T12_3_1 12.3.1: 

Parsing a pdf into individual images - 1 point

#### @anchor T12_3_2 12.3.2: 

Saving each individual image specified in 2.1 in an individual file - 3 points

#### @anchor T12_3_3 12.3.3: 

Saving each page into a slideshow for a powerpoint file extension - 3 points

#### @anchor T12_4_0 12.4.0: 

Save Pdf content in an excel spreadsheet format 
	(I need more help on this part, seeing as grids are picky and I don't know what I'm doing here) - 8 points

## User Story 13 : As a user, I want to be able to comment on any part of the PDF so that I can effectively add extra details or communicate with others who might view it.

### Tasks

#### @anchor T13_1 13.1

Decide whether to implement this via a button/right-click dropdown. Once implemented users click to insert a comment. 

#### @anchor T13_2 13.2

Implement a button to toggle comments. Once implemented users could toggle whether comments show. 

## User Story 14: As a user, I want a GUI to better work with and the pdf files.

### Tasks

#### @anchor T14_1 14.1

create a view box to display a pdf document (2 point)

#### @anchor T14_2 14.2

create tool ribbon at the top of the screen to access different tools/commands (2 point)

#### @anchor T14_2_1 14.2.1

create a ribbon tab for file i/o (1 point)

#### @anchor T14_2_2 14.2.2

create a ribbon tab for drawing tools (1 point)

#### @anchor T14_2_3 14.2.3

create a ribbon tab for help and documentation (1 point)

#### @anchor T14_3 14.3

create toolbox on the side to display useful tools for manipulating the document (5 points)
