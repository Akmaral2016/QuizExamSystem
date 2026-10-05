import java.util.ArrayList;
import java.util.Scanner;

public class QuizExamSystem {

    // =========================
    // QUESTION
    // =========================

    static class Question {

        private String questionText;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String correctAnswer;

        public Question(
                String questionText,
                String optionA,
                String optionB,
                String optionC,
                String optionD,
                String correctAnswer
        ) {
            this.questionText = questionText;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getOptionA() {
            return optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }


    // =========================
    // SUBJECT
    // =========================

    static class Subject {

        private String name;

        public Subject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    // =========================
    // EXAM
    // =========================

    static class Exam {

        private String examName;
        private Subject subject;
        private ArrayList<Question> questions;

        public Exam(String examName, Subject subject) {
            this.examName = examName;
            this.subject = subject;
            this.questions = new ArrayList<>();
        }

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public String getExamName() {
            return examName;
        }

        public Subject getSubject() {
            return subject;
        }

        public ArrayList<Question> getQuestions() {
            return questions;
        }
    }


    // =========================
    // STUDENT
    // =========================

    static class Student {

        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    // =========================
    // ANSWER
    // =========================

    static class Answer {

        private Question question;
        private String userAnswer;

        public Answer(Question question, String userAnswer) {
            this.question = question;
            this.userAnswer = userAnswer;
        }

        public Question getQuestion() {
            return question;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public boolean isCorrect() {
            return userAnswer.equals(
                    question.getCorrectAnswer()
            );
        }
    }


    // =========================
    // RESULT
    // =========================

    static class Result {

        private Student student;
        private Exam exam;
        private int score;
        private int totalQuestions;
        private ArrayList<Answer> answers;

        public Result(
                Student student,
                Exam exam,
                int score,
                int totalQuestions,
                ArrayList<Answer> answers
        ) {
            this.student = student;
            this.exam = exam;
            this.score = score;
            this.totalQuestions = totalQuestions;
            this.answers = answers;
        }

        public void showResult() {

            double percentage =
                    (double) score / totalQuestions * 100;

            System.out.println();
            System.out.println("================================");
            System.out.println("          📊 RESULT");
            System.out.println("================================");

            System.out.println(
                    "Student: " + student.getName()
            );

            System.out.println(
                    "Exam: " + exam.getExamName()
            );

            System.out.println(
                    "Subject: " + exam.getSubject().getName()
            );

            System.out.println(
                    "Score: " + score + "/" + totalQuestions
            );

            System.out.printf(
                    "Percentage: %.1f%%%n",
                    percentage
            );

            System.out.println("================================");
        }

        public void reviewAnswers() {

            System.out.println();
            System.out.println("================================");
            System.out.println("          📋 REVIEW");
            System.out.println("================================");

            int number = 1;

            for (Answer answer : answers) {

                Question question =
                        answer.getQuestion();

                System.out.println();
                System.out.println(
                        number + ". "
                                + question.getQuestionText()
                );

                System.out.println(
                        "Your answer: "
                                + answer.getUserAnswer()
                );

                System.out.println(
                        "Correct answer: "
                                + question.getCorrectAnswer()
                );

                if (answer.isCorrect()) {
                    System.out.println("✅ Correct");
                } else {
                    System.out.println("❌ Wrong");
                }

                number++;
            }

            System.out.println();
            System.out.println("================================");
        }
    }


    // =========================
    // SYSTEM
    // =========================

    private Scanner scanner;

    public QuizExamSystem() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }


    // =========================
    // START EXAM
    // =========================

    public Result startExam(
            Student student,
            Exam exam
    ) {

        int score = 0;
        int questionNumber = 1;

        ArrayList<Answer> answers =
                new ArrayList<>();

        System.out.println();
        System.out.println("================================");
        System.out.println("        🧠 START EXAM");
        System.out.println("================================");

        System.out.println(
                "Student: " + student.getName()
        );

        System.out.println(
                "Subject: "
                        + exam.getSubject().getName()
        );

        System.out.println(
                "Exam: " + exam.getExamName()
        );

        System.out.println("================================");


        for (Question question : exam.getQuestions()) {

            System.out.println();
            System.out.println(
                    "Question " + questionNumber
            );

            System.out.println(
                    question.getQuestionText()
            );

            System.out.println(
                    "A) " + question.getOptionA()
            );

            System.out.println(
                    "B) " + question.getOptionB()
            );

            System.out.println(
                    "C) " + question.getOptionC()
            );

            System.out.println(
                    "D) " + question.getOptionD()
            );


            // =========================
            // INPUT VALIDATION
            // =========================

            String answer;

            while (true) {

                System.out.print("Your answer: ");

                answer =
                        scanner.nextLine()
                                .trim()
                                .toUpperCase();

                if (
                        answer.equals("A")
                        || answer.equals("B")
                        || answer.equals("C")
                        || answer.equals("D")
                ) {
                    break;
                }

                System.out.println(
                        "⚠️ Please enter A, B, C, or D."
                );
            }


            // =========================
            // CHECK ANSWER
            // =========================

            Answer userAnswer =
                    new Answer(
                            question,
                            answer
                    );

            answers.add(userAnswer);


            if (userAnswer.isCorrect()) {

                System.out.println("✅ Correct!");

                score++;

            } else {

                System.out.println("❌ Wrong!");

                System.out.println(
                        "Correct answer: "
                                + question.getCorrectAnswer()
                );
            }

            questionNumber++;
        }


        return new Result(
                student,
                exam,
                score,
                exam.getQuestions().size(),
                answers
        );
    }


    // =========================
    // MENU
    // =========================

    public void showMenu(
            Student student,
            Exam exam
    ) {

        while (true) {

            System.out.println();
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║     🧠 QUIZ EXAM SYSTEM     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Start Exam               ║");
            System.out.println("║ 2. Exit                     ║");
            System.out.println("╚══════════════════════════════╝");

            System.out.print("Choose: ");

            String choice =
                    scanner.nextLine().trim();


            if (choice.equals("1")) {

                Result result =
                        startExam(
                                student,
                                exam
                        );


                // =========================
                // RESULT MENU
                // =========================

                while (true) {

                    System.out.println();
                    System.out.println(
                            "╔══════════════════════════════╗"
                    );

                    System.out.println(
                            "║       📊 EXAM FINISHED       ║"
                    );

                    System.out.println(
                            "╠══════════════════════════════╣"
                    );

                    System.out.println(
                            "║ 1. Show Result              ║"
                    );

                    System.out.println(
                            "║ 2. Review Answers           ║"
                    );

                    System.out.println(
                            "║ 3. Back to Main Menu        ║"
                    );

                    System.out.println(
                            "╚══════════════════════════════╝"
                    );

                    System.out.print("Choose: ");

                    String resultChoice =
                            scanner.nextLine().trim();


                    if (resultChoice.equals("1")) {

                        result.showResult();

                    } else if (resultChoice.equals("2")) {

                        result.reviewAnswers();

                    } else if (resultChoice.equals("3")) {

                        break;

                    } else {

                        System.out.println(
                                "⚠️ Invalid choice."
                        );
                    }
                }


            } else if (choice.equals("2")) {

                System.out.println();
                System.out.println(
                        "👋 Thank you for using Quiz Exam System!"
                );

                break;


            } else {

                System.out.println(
                        "⚠️ Invalid choice."
                );
            }
        }
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        // =========================
        // START SYSTEM
        // =========================

        QuizExamSystem system =
                new QuizExamSystem();


        // =========================
        // SUBJECT
        // =========================

        Subject java =
                new Subject("Java");


        // =========================
        // STUDENT REGISTRATION
        // =========================

        System.out.println();
        System.out.println("================================");
        System.out.println("       👤 STUDENT REGISTRATION");
        System.out.println("================================");

        System.out.print("Enter your name: ");

        String studentName =
                system.getScanner()
                        .nextLine()
                        .trim();

        Student student =
                new Student(studentName);

        System.out.println();
        System.out.println(
                "👋 Welcome, " + student.getName() + "!"
        );


        // =========================
        // EXAM
        // =========================

        Exam exam =
                new Exam(
                        "Java Core Exam",
                        java
                );


        // =========================
        // QUESTIONS
        // =========================

        Question q1 =
                new Question(
                        "What is Java?",
                        "Programming language",
                        "Database",
                        "Operating system",
                        "Browser",
                        "A"
                );


        Question q2 =
                new Question(
                        "Which keyword creates a class?",
                        "object",
                        "class",
                        "new",
                        "static",
                        "B"
                );


        Question q3 =
                new Question(
                        "Which keyword is used to create an object?",
                        "class",
                        "this",
                        "new",
                        "void",
                        "C"
                );


        Question q4 =
                new Question(
                        "Which access modifier provides the most restricted access?",
                        "public",
                        "protected",
                        "default",
                        "private",
                        "D"
                );


        Question q5 =
                new Question(
                        "Which keyword is used for inheritance?",
                        "extends",
                        "implements",
                        "inherits",
                        "superclass",
                        "A"
                );


        // =========================
        // ADD QUESTIONS
        // =========================

        exam.addQuestion(q1);
        exam.addQuestion(q2);
        exam.addQuestion(q3);
        exam.addQuestion(q4);
        exam.addQuestion(q5);


        // =========================
        // START MENU
        // =========================

        system.showMenu(
                student,
                exam
        );
    }
}
