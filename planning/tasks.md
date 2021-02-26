# Tasks

## User Story 1: As a user, I sometimes put the pages in the incorrect order. Could you give me a way to re-order the "pages" in a PDF so they make sense?

### Tasks

#### 1.1

Show all the pages in a navigation bar with page number.(3 points)

#### 1.2

Drag and drop [event] feature to reorder the desired page. (2 points)

#### 1.3

Reorder the PDF (1 point)

## User Story 2: As a reader, I want to be able to have a jump to any "page" of the pdf to make it easier to read over time and so I don't have to spend 4-5 minutes scrolling, OR a bookmark feature to click on to jump back to.

### Tasks

#### 2.1 

Implement a text field that allows the user to quickly jump to any "page" of the pdf that is requested. (2 pt)

#### 2.2

Implement a button that when clicked bookmarks the page currently on and added it to a list that can later be accessed. (2 pt)

## User Story 3: As a user, having a displayable word and character count when dealing with PDFs with raw text would be beneficial for adhering to document guidelines.

### Tasks

#### 3.1

Implement a displayable text box into the PDF editor GUI that displays a counter. (1 pt)

#### 3.2

Using a reader, scan the document for raw text and increment a counter based on either individual characters or whole words. (2 pt)

## User Story 4: As a user, I would like to be able to merge multiple PDF files into one so that I can easily consolidate information across multiple files into one document.

### Tasks

#### 4.1

The merge functionality needs to be implemented. This will require creating a new PDF that is made up of two or more separate PDF files. (2 points)

## User Story 5: As a user, I want an easy way to be able to select multiple occurrences of the same word so that I can edit  them all at once.

### Tasks

#### 5.1

Implement a text field that scans the document for all occurences of a word and highlights them. (1 pt)

#### 5.2

Implement a text field that replaces all instances of a word that has been discovered previously. (1 pt)

## User Story 6: As a user, I want to be able to highlight text in a PDF document to make it easier to refer back to important information.

### Tasks

#### 6.1

Select functionality needs to be implemented. Once functionality is implemented, a user will be able to select a word. (1 point)

#### 6.2

The coloring functionality needs to be implemented. Once implemented, the user will be able to color text. (1 point)

#### 6.3

The persistence strategy for highlighted words needs to be designed. Once the persistence strategy is designed, the design can be used to implement the persistence strategy. (1 point)

#### 6.4

The persistence strategy needs to be implemented. Once implemented, a user will be able to save specific words to be highlighted in the future. (1 point)

#### 6.5

The select and coloring functionality and the persistence strategy need to be combined to form the highlighting functionality. (1 point)

## User Story 7

### Tasks

#### 7.1

{ description }

#### 7.2

{ description }

## User Story 8: As a UI Designer I want to create and export mock UI/UX designs, so that I can create sharable designs in a common format like .pdf

### Tasks

#### 8.1

add ability to create new pdf document (1 point)

#### 8.2

add ability to specify dimensions of document pages (1 point)

#### 8.3

add ability to import external assets into the program (3 points)

#### 8.4

add ability to place imported assets onto document (3 points)

#### 8.5

add ability to draw geometric shapes on document (3 points)

#### 8.6

add ability to move assets around within document (3 points)

#### 8.7

add ability to add text boxes to document (3 points)

#### 8.8

add ability to export pdf document (1 point)

## User Story 9: As a Student I want to be able to directly draw on and annotate pdfs, this will allow me to complete assignments easily, or take notes more efficiently.


### Tasks

#### 9.1

Create a bitmap drawing tool - 8 points

#### 9.2

ensure drawing can be erased without affecting whatever might be underneath - 3 points

## User Story 10: As an editor, an undo feature implemented through a toolbar button with unlimited undos would allow me to fix mistakes much more easily.

### Tasks

#### 10.1

Implement a button into the PDF editor GUI with an indicator that it is an undo button via text label or an "undo arrow" image. (1 pt)

#### 10.2

Create a data structure to keep track of the individual actions taken by the user to edit the PDF. (3 pt)

#### 10.3

When the undo button is pressed a move is retrieved from the structure and the move is reversed from the PDF. (2 pt)

## User Story 11: As a student, I want to be able to extract code from the PDF so that I can refer later to the required specific notes, i.e. code, without wandering in the whole PDF.

### Tasks

#### 11.1

Identify the code from the given page in the PDF.(2 points)

#### 11.2

Extract the identified code.(1 point)

#### 11.3

Provide option to select the existing file for appending the code or create a new file.(1 point)

## User Story 12: As a user, I want to convert PDF document to/from various file formats (docx, xlsx, pptx, jpg) without changing the alignment so that, I can manipulate it as I wish.

### Tasks

#### 12.1:

   Add file extension adaptability into the save window for various file types - 1 point

#### Task 12.2.0: 

   be able to read non-editable/handwritten text into typed text that is editable - 5 points

#### Task 12.3.0: 

Save Pdf "pages" as images that are viewable - 3 points

#### Task 12.3.1: 

Parsing a pdf into individual images - 1 point

#### Task 12.3.2: 

Saving each individual image specified in 2.1 in an individual file - 3 points

#### Task 12.3.3: 

Saving each page into a slideshow for a powerpoint file extension - 3 points

#### Task 12.4.0: 

Save Pdf content in an excel spreadsheet format 
	(I need more help on this part, seeing as grids are picky and I don't know what I'm doing here) - 8 points

## User Story 13

### Tasks

#### 13.1

{ description }

#### 13.2

{ description }

## User Story 14: As a user, I want a GUI to better work with and the pdf files.

### Tasks

#### 14.1

create a view box to display a pdf document (2 point)

#### 14.2

create tool ribbon at the top of the screen to access different tools/commands (2 point)

#### 14.2.1

create a ribbon tab for file i/o (1 point)

#### 14.2.2

create a ribbon tab for drawing tools (1 point)

#### 14.2.3

create a ribbon tab for help and documentation (1 point)

#### 14.3

create toolbox on the side to display useful tools for manipulating the document (5 points)
