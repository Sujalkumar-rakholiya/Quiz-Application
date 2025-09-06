package com.sujalkumar.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivityc extends AppCompatActivity {

    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    int wrong = 0;

    String[] questions = {
            "Which of the following is the correct syntax for a comment in C?",
            "Which data type is used to store a single character in C?",
            "Which keyword is used to declare a constant variable in C?",
            "Which header file is needed for input and output functions in C?",
            "Which of the following operators has the highest precedence in C?",
            "Which loop is guaranteed to execute at least once in C?",
            "What is the size of an int data type in C (on most modern systems)?",
            "What does malloc() function return in C?",
            "Which of the following is true about pointers in C?",
            "Which keyword is used to define a macro in C?"
    };
    String[] options = {
            "// This is a comment","/* This is a comment */","# This is a comment","Both a) and b)",
            "char","string","character","text",
            "const","final","static","constant",
            "#include <stdio.h>","#include <stdlib.h>","#include <conio.h>","#include <string.h>",
            "+","*","&&","=",
            "for","while","do-while","foreach",
            "2 bytes","4 bytes","8 bytes","Depends on the compiler",
            "Pointer to allocated memory","Integer","Boolean","None",
            "Pointers store addresses of variables.","Pointers can be NULL.","Pointers can point to other pointers.","All of the above.",
            "macro","#define","const","#macro"
    };
    String[] answers = {
            "Both a) and b)",
            "char",
            "const",
            "#include <stdio.h>",
            "*",
            "do-while",
            "Depends on the compiler",
            "Pointer to allocated memory",
            "All of the above.",
            "#define"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_c);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quitBtn = findViewById(R.id.quitBtn);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        dispNo = findViewById(R.id.dispNo);
        next = findViewById(R.id.nextBtn);
        radio_g = findViewById(R.id.answerGroup);
        rb1 = findViewById(R.id.radioBtn1);
        rb2 = findViewById(R.id.radioBtn2);
        rb3 = findViewById(R.id.radioBtn3);
        rb4 = findViewById(R.id.radioBtn4);

        question.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radio_g.getCheckedRadioButtonId() == -1){
                    Toast.makeText(QuestionActivityc.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uAnswer.getText().toString();

                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivityc.this, "Hurray!! You are correct", Toast.LENGTH_SHORT).show();
                }else {
                    wrong++;
                    Toast.makeText(QuestionActivityc.this, "Oops!! You are wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if (score != null){
                    score.setText(""+correct);
                    if (flag<questions.length){
                        question.setText(questions[flag]);
                        rb1.setText(options[flag * 4]);
                        rb2.setText(options[flag * 4 + 1]);
                        rb3.setText(options[flag * 4 + 2]);
                        rb4.setText(options[flag * 4 + 3]);

                        dispNo.setText((flag + 1) + "/" + questions.length);
                    }else {
                        marks = correct;
                        Intent intent = new Intent(QuestionActivityc.this, ResultActivity.class);
                        intent.putExtra("attempted", flag);
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        startActivity(intent);
                        finish();
                    }

                    radio_g.clearCheck();
                }

            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivityc.this, ResultActivity.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}