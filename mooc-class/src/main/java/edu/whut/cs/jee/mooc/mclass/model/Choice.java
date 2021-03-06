package edu.whut.cs.jee.mooc.mclass.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择题
 */
@Entity
@Table(name = "mclass_choice")
@PrimaryKeyJoinColumn(name = "choice_id")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Choice extends Subject implements Cloneable{

    /**
     * 候选项
     */
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="choice_id")
    private List<Option> options;

    @Override
    public boolean check(String answer) {
        return answer.equalsIgnoreCase(this.getCorrectOptionNames());
    }

    private String getCorrectOptionNames() {
        String correctOptionNames = "";
        for (Option option : options) {
            if(option.isCorrect()) {
                correctOptionNames += option.getName() + ",";
            }
        }
        return correctOptionNames.length() > 0 ? correctOptionNames.substring(0, correctOptionNames.length() - 1) : "";
    }

    @Override
    public Object clone() {
        Choice choice = null;
        choice = (Choice)super.clone();
        Option option = null;
        List<Option> options = new ArrayList<>();
        for(Option option1 : this.options) {
            option = (Option)option1.clone();
            options.add(option);
        }
        choice.setOptions(options);
        return choice;
    }

}