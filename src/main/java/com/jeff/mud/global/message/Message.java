package com.jeff.mud.global.message;

import com.jeff.mud.template.Template;
import lombok.Getter;

@Getter
public class Message {
  private final Template template;
  private final Object payload;

  public Message(Template template, Object payload) {
    this.template = template;
    this.payload = payload;
  }
}
