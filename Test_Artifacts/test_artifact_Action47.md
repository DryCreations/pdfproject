# Allow user to open existing documents - Action 47

## Associated Task or Action identifier

Action 47

## Screenshot and its description
![Action_47](https://user-images.githubusercontent.com/43549673/114035940-971b8900-984d-11eb-9aa4-555f1ad33feb.png)

### Description about screenshot
Added a button under File -> Open Document to open existing pdf documents


## Notes/ comments

While testing this it has come to attention that the exported pdf from itext7 is slightly malformed, likely the same issue here https://stackoverflow.com/questions/61364972/why-is-my-xref-table-corrupt-after-removing-or-adding-annotations

It is displayable by most pdf readers, however the one this project use to display pdfs will not work. So this project cannot reopen pdf's exported by this application. To solve this it seems modifying itext7 code would be necessary. Or this project would need some way to detect the malformed xref table and fix it when we read a file.

otherwise opening pdf's that are not malformed works fine.
