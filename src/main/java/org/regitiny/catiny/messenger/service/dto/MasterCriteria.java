package org.regitiny.catiny.messenger.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link org.regitiny.catiny.messenger.domain.Master} entity. This class is used
 * in {@link org.regitiny.catiny.messenger.web.rest.MasterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /masters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MasterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private UUIDFilter masterId;

    private LongFilter userId;

    private LongFilter groupId;

    private LongFilter companyId;

    private StringFilter masterUserName;

    private StringFilter userName;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter imageUrl;

    private StringFilter email;

    private StringFilter groupName;

    private StringFilter companyName;

    private InstantFilter createdDate;

    private InstantFilter modifiedDate;

    public MasterCriteria() {
    }

    public MasterCriteria(MasterCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.masterId = other.masterId == null ? null : other.masterId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.companyId = other.companyId == null ? null : other.companyId.copy();
        this.masterUserName = other.masterUserName == null ? null : other.masterUserName.copy();
        this.userName = other.userName == null ? null : other.userName.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.groupName = other.groupName == null ? null : other.groupName.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
    }

    @Override
    public MasterCriteria copy() {
        return new MasterCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public UUIDFilter getMasterId() {
        return masterId;
    }

    public void setMasterId(UUIDFilter masterId) {
        this.masterId = masterId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(LongFilter groupId) {
        this.groupId = groupId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public StringFilter getMasterUserName() {
        return masterUserName;
    }

    public void setMasterUserName(StringFilter masterUserName) {
        this.masterUserName = masterUserName;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(StringFilter imageUrl) {
        this.imageUrl = imageUrl;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getGroupName() {
        return groupName;
    }

    public void setGroupName(StringFilter groupName) {
        this.groupName = groupName;
    }

    public StringFilter getCompanyName() {
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public InstantFilter getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(InstantFilter modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MasterCriteria that = (MasterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(masterId, that.masterId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(companyId, that.companyId) &&
            Objects.equals(masterUserName, that.masterUserName) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(imageUrl, that.imageUrl) &&
            Objects.equals(email, that.email) &&
            Objects.equals(groupName, that.groupName) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(modifiedDate, that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        masterId,
        userId,
        groupId,
        companyId,
        masterUserName,
        userName,
        firstName,
        lastName,
        imageUrl,
        email,
        groupName,
        companyName,
        createdDate,
        modifiedDate
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MasterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (masterId != null ? "masterId=" + masterId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (masterUserName != null ? "masterUserName=" + masterUserName + ", " : "") +
                (userName != null ? "userName=" + userName + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (groupName != null ? "groupName=" + groupName + ", " : "") +
                (companyName != null ? "companyName=" + companyName + ", " : "") +
                (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
                (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            "}";
    }

}
