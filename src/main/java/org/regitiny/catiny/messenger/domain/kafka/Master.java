package org.regitiny.catiny.messenger.domain.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A Master.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Master implements Serializable
{
  private static final long serialVersionUID = 1L;

  private UUID masterId;

  private Long userId;

  private Long groupId;

  private Long companyId;

  private String masterUserName;

  private String userName;

  private String firstName;

  private String lastName;

  private String imageUrl;

  private String email;

  private String groupName;

  private String companyName;

  private Instant createdDate;

  private Instant modifiedDate;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Master masterId(UUID masterId)
  {
    this.masterId = masterId;
    return this;
  }

  public Master userId(Long userId)
  {
    this.userId = userId;
    return this;
  }

  public Master groupId(Long groupId)
  {
    this.groupId = groupId;
    return this;
  }

  public Master companyId(Long companyId)
  {
    this.companyId = companyId;
    return this;
  }

  public Master masterUserName(String masterUserName)
  {
    this.masterUserName = masterUserName;
    return this;
  }

  public Master userName(String userName)
  {
    this.userName = userName;
    return this;
  }

  public Master firstName(String firstName)
  {
    this.firstName = firstName;
    return this;
  }

  public Master lastName(String lastName)
  {
    this.lastName = lastName;
    return this;
  }

  public Master imageUrl(String imageUrl)
  {
    this.imageUrl = imageUrl;
    return this;
  }

  public Master email(String email)
  {
    this.email = email;
    return this;
  }

  public Master groupName(String groupName)
  {
    this.groupName = groupName;
    return this;
  }

  public Master companyName(String companyName)
  {
    this.companyName = companyName;
    return this;
  }

  public Master createdDate(Instant createdDate)
  {
    this.createdDate = createdDate;
    return this;
  }

  public Master modifiedDate(Instant modifiedDate)
  {
    this.modifiedDate = modifiedDate;
    return this;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof Master))
    {
      return false;
    }
    return masterId != null && masterId.equals(((Master) o).masterId);
  }

  @Override
  public int hashCode()
  {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "Master{" +
      "  masterId='" + getMasterId() + "'" +
      ", userId=" + getUserId() +
      ", groupId=" + getGroupId() +
      ", companyId=" + getCompanyId() +
      ", masterUserName='" + getMasterUserName() + "'" +
      ", userName='" + getUserName() + "'" +
      ", firstName='" + getFirstName() + "'" +
      ", lastName='" + getLastName() + "'" +
      ", imageUrl='" + getImageUrl() + "'" +
      ", email='" + getEmail() + "'" +
      ", groupName='" + getGroupName() + "'" +
      ", companyName='" + getCompanyName() + "'" +
      ", createdDate='" + getCreatedDate() + "'" +
      ", modifiedDate='" + getModifiedDate() + "'" +
      "}";
  }
}
