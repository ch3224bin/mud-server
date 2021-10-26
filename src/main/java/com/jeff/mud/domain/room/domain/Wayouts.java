package com.jeff.mud.domain.room.domain;

import com.google.common.base.Strings;
import com.jeff.mud.domain.room.constants.Direction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Wayouts {
  @OneToMany(mappedBy = "room")
  private List<Wayout> wayouts = new ArrayList<>();

  public List<Wayout> getSortedWayouts() {
    return wayouts.stream()
      .sorted()
      .collect(Collectors.toList());
  }

  public String getExitString() {
    String exitString = this.wayouts.stream()
      .filter(Wayout::isShow)
      .map(Wayout::toString)
      .collect(Collectors.joining(" "));
    return Strings.isNullOrEmpty(exitString.strip()) ? "없음" : exitString;
  }

  public Optional<Wayout> getWayoutByDirection(Direction direction) {
    return this.wayouts.stream()
      .filter(wayout -> wayout.getDirection() == direction)
      .findFirst();
  }

  public void add(Wayout wayout) {
    if (wayout == null) {
      throw new NullPointerException("argument is null");
    }

    if (this.wayouts == null) {
      this.wayouts = new ArrayList<>();
    }
    this.wayouts.add(wayout);
  }
}
