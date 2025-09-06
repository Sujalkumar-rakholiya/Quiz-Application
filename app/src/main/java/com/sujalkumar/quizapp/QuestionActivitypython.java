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

public class QuestionActivitypython extends AppCompatActivity {

    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    int wrong = 0;

    String[] questions = {
            "What will type(5.0) return in Python?",
            "Which of the following is used to take input from the user in Python?",
            "Which keyword is used to define a function in Python?",
            "Which data type is immutable in Python?",
            "Which of the following methods is used to remove an element from a list by index?",
            "Which statement is used to handle exceptions in Python?",
            "What will set([1,2,2,3,4,4]) return?",
            "What does the lambda keyword define in Python?",
            "Which of the following decorators is used to define a static method in Python?",
            "Which of the following modules is used for working with regular expressions in Python?"
    };
    String[] options = {
            "int","float","double","str",
            "input()","scan()","cin>>","read()",
            "define","def","function","func",
            "list","set","dictionary","tuple",
            "delete()","remove()","pop()","discard()",
            "catch","try-except","handle","throw",
            "{1, 2, 2, 3, 4, 4}","{1, 2, 3, 4}","[1, 2, 3, 4]","Error",
            "Anonymous function","Loop","Class","Dictionary",
            "@staticmethod","@class","@classmethod","@static",
            "re","regex","string","regexp"
    };
    String[] answers = {
            "float",
            "input()",
            "def",
            "tuple",
            "pop()",
            "try-except",
            "{1, 2, 3, 4}",
            "Anonymous function",
            "@staticmethod",
            "re"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_python);
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
                    Toast.makeText(QuestionActivitypython.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uAnswer.getText().toString();

                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivitypython.this, "Hurray!! You are correct", Toast.LENGTH_SHORT).show();
                }else {
                    wrong++;
                    Toast.makeText(QuestionActivitypython.this, "Oops!! You are wrong", Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(QuestionActivitypython.this, ResultActivity.class);
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
                Intent intent = new Intent(QuestionActivitypython.this, ResultActivity.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}