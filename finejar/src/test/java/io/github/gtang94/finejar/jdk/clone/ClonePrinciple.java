package io.github.gtang94.finejar.jdk.clone;

import org.junit.Test;

import java.util.Objects;

public class ClonePrinciple {

    class Subject {
        int index;
        String title;

        public Subject(int index, String title) {
            this.index = index;
            this.title = title;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Subject{" +
                    "index=" + index +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    class Topic implements Cloneable {
        String name;
        Subject subject;

        public Topic(String name, Subject subject) {
            this.name = name;
            this.subject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Object clone() {
            try {
                return super.clone();
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String toString() {
            return "Topic{" +
                    "name='" + name + '\'' +
                    ", subject=" + subject +
                    '}';
        }
    }

    @Test
    public void shallowCloneTest() {
        // 构造源对象
        Subject subject = new Subject(0, "subject");
        Topic sourceObj = new Topic("源topic对象", subject);

        // 拷贝对象
        Topic cloneObj1 = (Topic) sourceObj.clone();
//        System.out.println("源对象： " + sourceObj.toString());
//        System.out.println("拷贝对象：" + cloneObj1.toString());

        // 修改拷贝对象的基本对象
//        cloneObj1.setName("修改拷贝的这个对象");
//        System.out.println("源对象： " + sourceObj.toString());
//        System.out.println("拷贝对象: " + cloneObj1.toString());

        sourceObj.getSubject().setTitle("修改拷贝的这个对象的属性的对象属性");
//        cloneObj1.getSubject().setTitle("修改拷贝的对象属性");
//        System.out.println("源对象： " + sourceObj.toString());
//        System.out.println("拷贝对象: " + cloneObj1.toString());

//        sourceObj.setName("修改源对象");
//        sourceObj.setSubject(new Subject(300, "修改源对象"));
        System.out.println("源对象: " + sourceObj);
        System.out.println("拷贝对象" + cloneObj1.toString());

    }

    class SubjectDeep implements Cloneable {
        int index;
        String title;

        public SubjectDeep(int index, String title) {
            this.index = index;
            this.title = title;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String toString() {
            return "Subject{" +
                    "index=" + index +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    class TopicDeep implements Cloneable {
        String name;
        SubjectDeep subject;

        public TopicDeep(String name, SubjectDeep subject) {
            this.name = name;
            this.subject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SubjectDeep getSubject() {
            return subject;
        }

        public void setSubject(SubjectDeep subject) {
            this.subject = subject;
        }

        @Override
        public Object clone() {
            try {
                TopicDeep topicDeep = (TopicDeep) super.clone();
                topicDeep.subject = (SubjectDeep) this.subject.clone();
                return topicDeep;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String toString() {
            return "Topic{" +
                    "name='" + name + '\'' +
                    ", subject=" + subject +
                    '}';
        }
    }

    @Test
    public void deepCloneTest() {

        // 构造源对象
        SubjectDeep subject = new SubjectDeep(0, "subject");
        TopicDeep sourceObj = new TopicDeep("源topic对象", subject);

        // 拷贝对象
        TopicDeep cloneObj1 = (TopicDeep) sourceObj.clone();

        sourceObj.getSubject().setTitle("修改拷贝的这个对象的属性的对象属性");

        System.out.println("源对象: " + sourceObj.toString());
        System.out.println("拷贝对象: " + cloneObj1.toString());
    }

}
