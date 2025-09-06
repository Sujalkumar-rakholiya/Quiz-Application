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

public class QuestionActivitysql extends AppCompatActivity {

    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    int wrong = 0;

    String[] questions = {
            "What does SQL stand for?",
            "Which SQL statement is used to retrieve data from a database?",
            "Which SQL clause is used to filter records?",
            "Which SQL command is used to remove a table from a database?",
            "Which SQL keyword is used to sort the result set?",
            "Which SQL function is used to count the number of records in a table?",
            "Which SQL operator is used to check if a value exists within a given set of values?",
            "Which SQL function returns the current date and time?",
            "Which SQL function is used to find the highest value in a column?",
            "Which SQL keyword is used to prevent duplicate values in a query result?"
    };
    String[] options = {
            "Structured Query Language","Sequential Query Language","System Query Language","Standard Query Language",
            "SELECT","RETRIEVE","GET","SHOW",
            "WHERE","ORDER BY","GROUP BY","HAVING",
            "REMOVE TABLE","DROP TABLE","DELETE TABLE","TRUNCATE TABLE",
            "SORT BY","ORDER","ORDER BY","ARRANGE BY",
            "SUM()","COUNT()","TOTAL()","NUMBER()",
            "EXISTS","BETWEEN","IN","LIKE",
            "NOW()","CURRENT_DATE()","GETDATE()","SYSDATE()",
            "HIGHEST()","TOP()","MAX()","UPPER()",
            "UNIQUE","DISTINCT","FILTER","LIMIT"
    };
    String[] answers = {
            "Structured Query Language",
            "SELECT",
            "WHERE",
            "DROP TABLE",
            "ORDER BY",
            "COUNT()",
            "IN",
            "NOW()",
            "MAX()",
            "DISTINCT"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_sql);
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
                    Toast.makeText(QuestionActivitysql.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uAnswer.getText().toString();

                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivitysql.this, "Hurray!! You are correct", Toast.LENGTH_SHORT).show();
                }else {
                    wrong++;
                    Toast.makeText(QuestionActivitysql.this, "Oops!! You are wrong", Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(QuestionActivitysql.this, ResultActivity.class);
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
                Intent intent = new Intent(QuestionActivitysql.this, ResultActivity.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}