Tasks
User Story 1: As a user, I sometimes put the pages in the incorrect order. Could you give me a way to re-order the "pages" in a PDF so they make sense?
Tasks
1.1
\anchor t1_1

Show all the pages in a navigation bar with page number.(3 points)

1.2
\anchor t1_2

Drag and drop [event] feature to reorder the desired page. (2 points)

1.3
\anchor t1_3

Reorder the PDF (1 point)

User Story 2: As a reader, I want to be able to have a jump to any "page" of the pdf to make it easier to read over time and so I don't have to spend 4-5 minutes scrolling, OR a bookmark feature to click on to jump back to.
Tasks
2.1
\anchor t2_1

Implement a text field that allows the user to quickly jump to any "page" of the pdf that is requested. (2 pt)

2.2
\anchor t2_2

Implement a button that when clicked bookmarks the page currently on and added it to a list that can later be accessed. (2 pt)

User Story 3: As a user, having a displayable word and character count when dealing with PDFs with raw text would be beneficial for adhering to document guidelines.
Tasks
3.1
\anchor t3_1

Implement a displayable text box into the PDF editor GUI that displays a counter. (1 pt)

3.2
\anchor t3_2

Using a reader, scan the document for raw text and increment a counter based on either individual characters or whole words. (2 pt)

User Story 4: As a user, I would like to be able to merge multiple PDF files into one so that I can easily consolidate information across multiple files into one document.
Tasks
4.1
\anchor t4_1

The merge functionality needs to be implemented. This will require creating a new PDF that is made up of two or more separate PDF files. (2 points)

User Story 5: As a user, I want an easy way to be able to select multiple occurrences of the same word so that I can edit them all at once.
Tasks
5.1
\anchor t5_1

Implement a text field that scans the document for all occurences of a word and highlights them. (1 pt)

5.2
\anchor t5_2

Implement a text field that replaces all instances of a word that has been discovered previously. (1 pt)

User Story 6: As a user, I want to be able to highlight text in a PDF document to make it easier to refer back to important information.
Tasks
6.1
\anchor t6_1

Select functionality needs to be implemented. Once functionality is implemented, a user will be able to select a word. (1 point)

6.2
\anchor t6_2

The coloring functionality needs to be implemented. Once implemented, the user will be able to color text. (1 point)

6.3
\anchor t6_3

The persistence strategy for highlighted words needs to be designed. Once the persistence strategy is designed, the design can be used to implement the persistence strategy. (1 point)

6.4
\anchor t6_4

The persistence strategy needs to be implemented. Once implemented, a user will be able to save specific words to be highlighted in the future. (1 point)

6.5
\anchor t6_5

The select and coloring functionality and the persistence strategy need to be combined to form the highlighting functionality. (1 point)

User Story 7: As a contractor/consultant, adding an esign/everify to a pdf document whenever seems very useful.
Tasks
7.1
\anchor t7_1

Select what digital signature type to implement. Once implemented determine whether to find a signature library / build a library to use the EdDSA scheme.

7.2
\anchor t7_2

A button for users to add their digital signature to sign documents. Once implemented, users would get to save their digital signature on documents.

7.3
\anchor t7_3

A button to add signature and initialize areas. Once implemented users can add signature areas and initialize markup.

7.4
\anchor t7_4

Implment addon to verify a document was signed. Once implemented users get notified that a document was signed on opening a document.

User Story 8: As a UI Designer I want to create and export mock UI/UX designs, so that I can create sharable designs in a common format like .pdf
Tasks
8.1
\anchor t8_1

add ability to create new pdf document (1 point)

8.2
\anchor t8_2

add ability to specify dimensions of document pages (1 point)

8.3
\anchor t8_3

add ability to import external assets into the program (3 points)

8.4
\anchor t8_4

add ability to place imported assets onto document (3 points)

8.5
\anchor t8_5

add ability to draw geometric shapes on document (3 points)

8.6
\anchor t8_6

add ability to move assets around within document (3 points)

8.7
\anchor t8_7

add ability to add text boxes to document (3 points)

8.8
\anchor t8_8

add ability to export pdf document (1 point)

User Story 9: As a Student I want to be able to directly draw on and annotate pdfs, this will allow me to complete assignments easily, or take notes more efficiently.
Tasks
9.1
\anchor t9_1

Create a bitmap drawing tool - 8 points

9.2
\anchor t9_2

ensure drawing can be erased without affecting whatever might be underneath - 3 points

User Story 10: As an editor, an undo feature implemented through a toolbar button with unlimited undos would allow me to fix mistakes much more easily.
Tasks
10.1
\anchor t10_1

Implement a button into the PDF editor GUI with an indicator that it is an undo button via text label or an "undo arrow" image. (1 pt)

10.2
\anchor t10_2

Create a data structure to keep track of the individual actions taken by the user to edit the PDF. (3 pt)

10.3
\anchor t10_3

When the undo button is pressed a move is retrieved from the structure and the move is reversed from the PDF. (2 pt)

User Story 11: As a student, I want to be able to extract code from the PDF so that I can refer later to the required specific notes, i.e. code, without wandering in the whole PDF.
Tasks
11.1
\anchor t11_1

Identify the code from the given page in the PDF.(2 points)

11.2
\anchor t11_2

Extract the identified code.(1 point)

11.3
\anchor t11_3

Provide option to select the existing file for appending the code or create a new file.(1 point)

User Story 12: As a user, I want to convert PDF document to/from various file formats (docx, xlsx, pptx, jpg) without changing the alignment so that, I can manipulate it as I wish.
Tasks
12.1:
\anchor t12_1

Add file extension adaptability into the save window for various file types - 1 point

Task 12.2.0:
\anchor t12_2_0

be able to read non-editable/handwritten text into typed text that is editable - 5 points

Task 12.3.0:
\anchor t12_3_0

Save Pdf "pages" as images that are viewable - 3 points

Task 12.3.1:
\anchor t12_3_1

Parsing a pdf into individual images - 1 point

Task 12.3.2:
\anchor t12_3_2

Saving each individual image specified in 2.1 in an individual file - 3 points

Task 12.3.3:
\anchor t12_3_3

Saving each page into a slideshow for a powerpoint file extension - 3 points

Task 12.4.0:
\anchor t12_4_0

Save Pdf content in an excel spreadsheet format (I need more help on this part, seeing as grids are picky and I don't know what I'm doing here) - 8 points

User Story 13 : As a user, I want to be able to comment on any part of the PDF so that I can effectively add extra details or communicate with others who might view it.
Tasks
13.1
\anchor t13_1

Decide whether to implement this via a button/right-click dropdown. Once implemented users click to insert a comment.

13.2
\anchor t13_2

Implement a button to toggle comments. Once implemented users could toggle whether comments show.

User Story 14: As a user, I want a GUI to better work with and the pdf files.
Tasks
14.1
\anchor t14_1

create a view box to display a pdf document (2 point)

14.2
\anchor t14_2

create tool ribbon at the top of the screen to access different tools/commands (2 point)

14.2.1
\anchor t14_2_1

create a ribbon tab for file i/o (1 point)

14.2.2
\anchor t14_2_2

create a ribbon tab for drawing tools (1 point)

14.2.3
\anchor t14_2_3

create a ribbon tab for help and documentation (1 point)

14.3
\anchor t14_3

create toolbox on the side to display useful tools for manipulating the document (5 points)

User Story 15: As a developer I would like to set up developer tooling and environment.
Tasks
15.1
\anchor t15_1

Setup github actions to run tests automatically

15.2
\anchor t15_2

Generate doxygen upon commit using github actions

15.3
\anchor t15_3

Set up a jira board to manage tasks and development.