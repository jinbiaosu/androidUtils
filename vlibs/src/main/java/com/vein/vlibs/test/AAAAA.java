package com.vein.vlibs.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by htjc on 17/3/1.
 */

public class AAAAA {
    public static void main(String[] args) {

//        List<Person> persons=new ArrayList<>();
//        persons.add(new Person(1));
//        persons.add(new Person(3));
//        persons.add(new Person(4));
//        persons.add(new Person(2));
//        for (Person a : persons){
//            System.out.print(a.age+"\n");
//        }
//        Collections.sort(persons,new Conpares());
//        for (Person a : persons){
//            System.out.print(a.age+"\n");
//        }

        String aa="20130407183000000";
        String bb="20130407184000000";
        System.out.print(aa.compareTo(bb));

    }

   public static class Person {
        int age;

       public Person(int age) {
            this.age = age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }


    static class Conpares implements Comparator {
        @Override
        public int compare(Object o, Object t1) {
            Person person1 = (Person) o;
            Person person2 = (Person) t1;
            return person1.age - person2.age;
        }
    }
}
