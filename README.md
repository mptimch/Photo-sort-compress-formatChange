# Photo-sort-compress-formatChange
Application, that working with image and vidio metadata, can change format of images, compress and rename it. More in readMe file

Program functions:
-> Changes the formats of your images to jpg, png or webp
-> Crop the picture in an optimal way. For example if the picture is 400 * 800 pixels and you want to crop it and set the dimensions to 200 * 200, then the program will cut it to 100 * 200 for you. 
The app will keep the proportions and cut the largest side to the specified size. The function can be used at the same time as changing the format
-> compresses images as a percentage of their size.
IMPORTANT: This feature is not used in conjunction with format change.
-> Renames photos and videos by creation date. The creation date is looked up in the metadata.
How and when can you use it? Ok, you have a trip with friends, and then sent each other photos through the cloud or something else. Creation date inside Windows will be reset.
And now it will be impossible to collect photos in one folder in the order by creation date. You will not be able to show them to relatives or other friends, your photos will be in complete chaos.
But you can just run this program and specify the path to the folder with all the photos in it. You can even select a folder with subfolders - it doesn't matter, the program will process everything.
Your photos and videos will appear in the result folder, named like 20230512_213518.jpg (year-month-day_hour_minutes_seconds.original_format).
And now you have all photos videos named by creation date. It is good for storage, display on any device or forwarding.

IMPORTANT: this is a separate function. It will not change the format \ cut images and so on. If this function is enabled, other functions will not work.
IMPORTANT2: works with all photo formats (even DSLR and iPhone) and with some video formats (in some cases it will not determine the date of creation)

And some more tricks
-> The folder with incoming images can have subfolders. There is a recursive function, all files will be pulled into the program, nothing will be lost. Also you can specify the path to 1 file instead of a folder
-> Error files folder may appear in the results folder. Files that could not be processed will get there (for example, the photo does not have metadata)
-> If there is already a file with the same name in the results folder, a number will be added to its name and it will be saved. Relevant when subfolders exist
-> The photo and video format is taken not from the name, but from the analysis of the file's internals. So, you can rename the file from jpg to mp4 - this app will process it
-> Logs folder will appear next to the program. Everything is logged there in 3 files: exceptions (critical), errors (which I processed) and successful file processing


There are jar and exe files in project - you can easy download it and use app.

Currently the program does not have a visual interface (GUI), everything works based on the settings in the config.properties file. Put it next to the jar or exe file and make the necessary settings. Visual design of the program is in development

You can write me to Telegram @Mptimch with suggestions for improving the program. 
Besides I am looking for a job and will be glad to any offers.



Функционал программы:
-> Меняет форматы ваших изображений на jpg, png или webp
-> Обрезает изображение оптимальным способом. То есть, если картинка 400 * 800, а вы хотите ее обрезать и задали размеры 200 * 200, то итоговый размер будет до 100 * 200.
То есть, программа сохранит пропорции и обрежет наибольшую сторону до указанного размера. Функцию можно использовать одновременно с изменением формата изображения
-> Сжимает картинки в % от их веса. 
ВАЖНО: Эта функция не используется совместно с изменением формата.
-> Переименовывает фото и видео по дате создания. Дата создания ищется в метаданных. Как и когда вы можете это использовать:
Допустим, вы с друзьями поехали в путешествие, а потом отправили друг другу фотографии через облако или как-то иначе. Дата создания внутри Windows будет изменена на дату скачивания фоток.
И теперь нельзя будет собрать фотографии в одну папку в порядке по дате создания. Вы не сможете показать их родственникам или другим друзьям, в ваших фотках будет полный хаос.
Но можно просто запустить эту программу и указать путь к папке со всеми фотографиями в ней. Можно даже выбрать папку с подпапками - не беда, программа все обработает. 
Ваши фотографии появятся в папке результатов с именем dhjlt 20230512_213518.jpg (год-месяц-день_час_минуты_секунды.оригинальный_формат).
То есть, теперь все ваши фото и видео будут названы по дате их создания. Это удобно для хранения, показа на любом устройстве или пересылки кому-то. 

Ну и еще пара фишек программы:
-> Папка с входящими картинками может иметь вложенные папки. Для обхода используется рекурсивная функция, все файлы будут обработаны в программе, ничего не потеряется. Можно указать путь и к одному файлу вместо папки.
-> В папке с результатами может появиться папка Error files. Туда попадут файлы, которые не удалось обработать (например, у фотки нет метаданных)
-> Если в папке с результатами уже есть файл с таким именем - к его имени добавится цифра в конце, и он будет сохранен. Актуально при существовании вложенных папок
-> Формат фото и видео берется не из названия, а из анализа внутрянки файла. По идее, вы можете переименовать файл с jpg на mp4 - программа все равно обработает файл как надо
-> Рядом с программой появится папка logs. Туда все логируется в 3 файла: исключения (критичное), ошибки (что я предусмотрел и обработал) и успешные обработки файла

В проекте есть jar и exe файлы. Вы можете их скачать им быстро запустить программу при необходимости

В данный момент у программы нет визуального интерфейса, все работает на базе настроек в файле config.properties. Положите его рядом с jar или ехе файлом и внесите необходимые настройки. Визуальное оформление программы в разработке

По поводу изменения и улучшения программы можете писать мне в телеграм @Mptimch. 
В данный момент нахожусь в поисках работы, готов рассмотреть ваши предложения