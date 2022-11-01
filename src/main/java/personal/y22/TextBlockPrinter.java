package personal.y22.m10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextBlockPrinter {
     public static void main(String[] args) throws IOException {

          String htmlDoc = """
                    <!DOCTYPE html>
                           <html lang="en">
                           
                           <head>
                               <meta charset="UTF-8">
                               <meta name="author" content="Andre">
                               <meta name="description" content="This page contains all the things I am learning how to create as I learn HTML.">
                               <title>My First Web Page</title>
                               <link rel="icon"
                                     href="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/240/google/346/penguin_1f427.png"
                                     type="image">
                           </head>
                           
                           <body style="background-color:#7fff7f;">
                           <h1>Hello World!</h1>
                           <hr>
                           <h2> I'm ready to learn html</h2>
                           <br> this is some more text
                           <br> this is even more text
                           <br> I just want to add new lines
                           <br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; now I want to learn how to indent text
                           <hr>
                           <p>This is my first web page.</p>
                           <hr>
                           <h3> I'm ready to learn html</h3>
                           <hr>
                           <p>This is my first web page.</p>
                           <hr>
                           &lt; this is a less-than sign
                           <br>
                           &gt; this is a greater-than sign
                           <br>
                           this is my attempt at adding an address. Mail this letter to write to the Pope:
                           <address>
                               His Holiness, Pope Francis
                               <br> Apostolic Palace
                               <br> 00120 Vatican City.
                           </address>
                           <br> &copy; this is a copyright declaration
                           <br> I am going to try to explain what <abbr
                                   title="the third Partita for Solo Violin,
                                   in E major, out of a set of 6 sonatas and
                                   partitas written by Bach at the end of his time in Kothen ">
                               BWV 1006 </abbr> is
                           <br>
                           </body>
                           
                           </html>
                    """;

          String fileName = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\testingFile.html";

          BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
          writer.write(htmlDoc);
          writer.close();
     }
}

