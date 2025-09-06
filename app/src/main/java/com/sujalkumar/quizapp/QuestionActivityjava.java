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

public class QuestionActivityjava extends AppCompatActivity {

    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    int wrong = 0;

    String[] questions = {
            "What is the default value of a boolean variable in Java?",
            "Which of the following is NOT a Java access modifier?",
            "Which keyword is used to inherit a class in Java?",
            "Which method is called when an object is created in Java?",
            "Which of the following methods can be used to find the length of a string in Java?",
            "What is the default value of an instance variable in Java?",
            "Which of the following is not a valid Java identifier?",
            "Which of these access modifiers allows a variable to be accessed only within the same class?",
            "What is the correct way to declare an array in Java?",
            "What is the output of Math.floor(3.9)?"
    };
    String[] options = {
            "true","false","null","0",
            "private","protected","default","global",
            "implements","extends","inherits","super",
            "finalize()","new()","constructor","init()",
            "length()","size()","getLength()","lengthOf()",
            "Null","0","Depends on the data type","Not assigned automatically",
            "_myVar","2ndVar","$value","first_name",
            "public","private","protected","default",
            "int arr[] = new int(5);","int arr[] = new int[5];","int arr = new int[5];","int arr() = new int[5];",
            "3","4","3.9","Compilation error"
    };
    String[] answers = {
            "false",
            "global",
            "extends",
            "constructor",
            "length()",
            "Depends on the data type",
            "2ndVar",
            "private",
            "int arr[] = new int[5];",
            "3"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_java);
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
                    Toast.makeText(QuestionActivityjava.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uAnswer.getText().toString();

                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivityjava.this, "Hurray!! You are correct", Toast.LENGTH_SHORT).show();
                }else {
                    wrong++;
                    Toast.makeText(QuestionActivityjava.this, "Oops!! You are wrong", Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(QuestionActivityjava.this, ResultActivity.class);
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
                Intent intent = new Intent(QuestionActivityjava.this, ResultActivity.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}