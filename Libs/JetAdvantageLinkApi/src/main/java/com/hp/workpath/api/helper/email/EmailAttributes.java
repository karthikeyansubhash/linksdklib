// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.helper.email;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.CommonUtility;

import com.hp.workpath.api.CapabilitiesExceededException;
import com.hp.workpath.api.Convertor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>The sets of attributes for sending email.
 * An instance of this class is created using {@link Builder}.</p>
 *
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
@DeviceApi
public final class EmailAttributes implements Parcelable, Convertor {
    private int mVersion;
    private List<EmailAddressInfo> mToList;
    private List<EmailAddressInfo> mCcList;
    private List<EmailAddressInfo> mBccList;
    private EmailAddressInfo mFrom;
    private String mSubject;
    private String mMessage;
    private List<File> mAttachmentList;

    private EmailAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;

        mToList = builder.mToList;
        mCcList = builder.mCcList;
        mBccList = builder.mBccList;
        mFrom = builder.mFrom;
        mSubject = builder.mSubject;
        mMessage = builder.mMessage;
        mAttachmentList = builder.mAttachmentList;
    }

    @SuppressLint("RestrictedApi")
    private EmailAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mToList = new ArrayList<>();
        in.readTypedList(mToList, EmailAddressInfo.CREATOR);
        mCcList = new ArrayList<>();
        in.readTypedList(mCcList, EmailAddressInfo.CREATOR);
        mBccList = new ArrayList<>();
        in.readTypedList(mBccList, EmailAddressInfo.CREATOR);
        mFrom = in.readParcelable(EmailAddressInfo.class.getClassLoader());
        mSubject = in.readString();
        mMessage = in.readString();
        mAttachmentList = new ArrayList<>();
        in.readList(mAttachmentList, File.class.getClassLoader());
    }

    @SuppressLint("RestrictedApi")
    private EmailAttributes(Object object) {
        if (object instanceof com.hp.jetadvantage.link.api.helper.email.EmailAttributes) {
            mVersion = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getVersion();
            Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

            mToList = new ArrayList<>();
            List<com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo> emailAddressInfos = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getA();
            for (com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo emailAddressInfo : emailAddressInfos) {
                mToList.add(EmailAddressInfo.CREATOR_OBJ.createFromObject(emailAddressInfo));
            }

            mCcList = new ArrayList<>();
            List<com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo> emailAddressCCInfos = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getB();
            for (com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo emailAddressInfo : emailAddressCCInfos) {
                mCcList.add(EmailAddressInfo.CREATOR_OBJ.createFromObject(emailAddressInfo));
            }

            mBccList = new ArrayList<>();
            List<com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo> emailAddressBCCInfos = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getC();
            for (com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo emailAddressInfo : emailAddressBCCInfos) {
                mBccList.add(EmailAddressInfo.CREATOR_OBJ.createFromObject(emailAddressInfo));
            }

            com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo emailAddressInfo = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getD();
            mFrom = EmailAddressInfo.CREATOR_OBJ.createFromObject(emailAddressInfo);

            mSubject = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getE();
            mMessage = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getF();
            mAttachmentList = new ArrayList<>();
            List<File> attachments = ((com.hp.jetadvantage.link.api.helper.email.EmailAttributes) object).getG();
            mAttachmentList.addAll(attachments);
        }
    }

    /**
     * Retrieves "TO" recipients for email.
     *
     * @return <p> List of recipients for email.
     * <ul>
     * <li>Return can not be empty. Atlease one recipient should be added to the list.</li>
     * </ul>
     * </p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<EmailAddressInfo> getTo() {
        return mToList;
    }

    /**
     *
     * Retrieves "CC" recipients for email.
     *
     * @return <p> List of recipients for email.
     * <ul>
     * <li>Return can be empty</li>
     * <li>An empty list indicates that CC recipients for email not given.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<EmailAddressInfo> getCc() {
        return mCcList;
    }

    /**
     * Retrieves "BCC" recipients
     *
     * @return <p> List of recipients for email.
     * <ul>
     * <li>Return can be empty</li>
     * <li>An empty list indicates that BCC recipients for email not given.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<EmailAddressInfo> getBcc() {
        return mBccList;
    }

    /**
     * Retrieves email sender "FROM".
     *
     * @return <p> EmailAddressInfo sender address.
     * <ul>
     * <li>Return can not be empty and sender address must be provided for email.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public EmailAddressInfo getFrom() {
        return mFrom;
    }

    /**
     * Retrieves email subject.
     *
     * @return <p> EmailAddressInfo subject.
     * <ul>
     * <li>Return value can be empty , if no subject provided for email.</li>
     * <li>Returns the subject description, if subject is given for email.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(mSubject) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings("unused")
    public String getSubject() {
        return mSubject;
    }

    /**
     * Retrieves email body message.
     *
     * @return <p> EmailAddressInfo message.
     * <ul>
     * <li>Return value can be empty , if no message body provided for email.</li>
     * <li>Returns the message description, if message is given for email.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(mMessage) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings("unused")
    public String getMessage() {
        return mMessage;
    }

    /**
     * Retrieves email attachments.
     *
     * @return <p> List of {@link File} email.
     * <ul>
     * <li>Return can be empty</li>
     * <li>An empty list indicates that attachments for email not added.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<File> getAttachments() {
        return mAttachmentList;
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeTypedList(mToList);
        dest.writeTypedList(mCcList);
        dest.writeTypedList(mBccList);
        dest.writeParcelable(mFrom, 0);
        dest.writeString(mSubject);
        dest.writeString(mMessage);
        dest.writeList(mAttachmentList);
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide parcelable implementation
     */
    public static final Creator<EmailAttributes> CREATOR = new Creator<EmailAttributes>() {
        @Override
        public EmailAttributes createFromParcel(Parcel in) {
            return new EmailAttributes(in);
        }

        @Override
        public EmailAttributes[] newArray(int size) {
            return new EmailAttributes[size];
        }
    };

    /**
     * @hide trivial
     */
    public static final ConvertorCreator<EmailAttributes> CREATOR_OBJ = new ConvertorCreator<EmailAttributes>() {
        @Override
        public EmailAttributes createFromObject(Object object) {
            return new EmailAttributes(object);
        }
    };

    /**
     * Builder for creating {@link EmailAttributes} containing email parameters.
     *
     * @since API 1
     */
    @DeviceApi
    public static class Builder {
        List<EmailAddressInfo> mToList = new ArrayList<>();
        List<EmailAddressInfo> mCcList = new ArrayList<>();
        List<EmailAddressInfo> mBccList = new ArrayList<>();
        EmailAddressInfo mFrom;
        String mSubject;
        String mMessage;
        List<File> mAttachmentList = new ArrayList<>();

        /**
         * Default constructor constructs a new Builder with default attributes.
         *
         * @since API 1
         */
        public Builder() {
        }

        /**
         * Adds "To" value to an email message. Includes optional "Name" parameter for recipient's display name.
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddress email address of the recipient
         * <ul>
         * <li>Value can not be empty. Atlease one recipient address should be added to the list.</li>
         * </ul>
         * </p>
         * @param <p>name name of the recipient
         * <ul>
         * <li>Value can be empty , if no name provided for email.</li>
         * </ul>
         * </p>
         * @return Builder builder with addToAddress.
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress/name) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder addToAddress(@NonNull final String emailAddress, @Nullable final String name) {
            mToList.add(new EmailAddressInfo(Preconditions.checkNotNull(emailAddress), name));
            return this;
        }

        /**
         * Adds multiple "To" values to an email message.
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddresses array of email addresses.
         * <ul>
         * <li>Value can not be empty. Atlease one recipient address should be added to the array.</li>
         * </ul>
         * </p>
         * @return Builder builder with addToAddresses.
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder addToAddresses(@NonNull String... emailAddresses) {
            for (String emailAddress : Preconditions.checkNotNull(emailAddresses)) {
                mToList.add(new EmailAddressInfo(emailAddress, null));
            }
            return this;
        }

        /**
         * Adds "Cc" value to an email message. Includes optional "Name" parameter for recipient's display name.
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddress email address of the recipient
         * <ul>
         * <li>Value can be empty , if no cc email address provided.</li>
         * </ul>
         * </p>
         * @param <p>name name of the recipient
         * <ul>
         * <li>Value can be empty , if no name provided for email.</li>
         * </ul>
         * </p>
         * @return Builder builder with addCcAddress.
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress/name) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder addCcAddress(@NonNull final String emailAddress, @Nullable final String name) {
            mCcList.add(new EmailAddressInfo(Preconditions.checkNotNull(emailAddress), name));
            return this;
        }

        /**
         * Adds multiple "Cc" values to an email message.
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddresses array of email addresses
         * <ul>
         * <li>Value can be empty , if no cc email address provided.</li>
         * </ul>
         * </p>
         * @return Builder builder with addCcAddresses.
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder addCcAddresses(@NonNull String... emailAddresses) {
            for (String emailAddress : Preconditions.checkNotNull(emailAddresses)) {
                mCcList.add(new EmailAddressInfo(emailAddress, null));
            }
            return this;
        }

        /**
         * Adds "Bcc" value to an email message. Includes optional "Name" parameter for recipient's display name.
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddress email address of the recipient.
         * <ul>
         * <li>Value can be empty , if no bcc email address provided.</li>
         * </ul>
         * </p>
         * @param <p>name name of the recipient.
         * <ul>
         * <li>Value can be empty , if no name provided for email.</li>
         * </ul>
         * </p>
         * @return Builder builder with addBccAddress.
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress/name) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder addBccAddress(@NonNull final String emailAddress, @Nullable final String name) {
            mBccList.add(new EmailAddressInfo(Preconditions.checkNotNull(emailAddress), name));
            return this;
        }

        /**
         * Adds multiple "Bcc" values to an email message.
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddresses array of email addresses
         * <ul>
         * <li>Value can be empty , if no bcc email address provided.</li>
         * </ul>
         * </p>
         * @return Builder builder with addBccAddresses.
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder addBccAddresses(@NonNull String... emailAddresses) {
            for (String emailAddress : Preconditions.checkNotNull(emailAddresses)) {
                mBccList.add(new EmailAddressInfo(emailAddress, null));
            }
            return this;
        }

        /**
         * Sets "From" for an email message
         * <p>This is an e-mail address in the form described by RFC 5322, section 3.4 (mailbox address),
         * except that such addresses may not contain new lines (but may contain comments).
         * Note that this means that an address may take either of the following forms:<br>
         * <ul><li>[display-name] &lt;addr-spec&gt;.</li></ul>
         * Examples: John Doe &lt;j.doe@acme.com&gt;.</p>
         *
         * @param <p>emailAddress email address of the sender.
         * <ul>
         * <li>Value can not be empty. Atlease one sender address should be provided.</li>
         * </ul>
         * </p>
         * @param <p>name name of the sender.
         * <ul>
         * <li>Value can be empty , if no name provided for sender email address.</li>
         * </ul>
         * </p>
         * @return this builder for method chaining
         * @exception NullPointerException Returns error if calling a method on a null reference(emailAddress/name) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder setFrom(@NonNull final String emailAddress, @Nullable final String name) {
            mFrom = new EmailAddressInfo(Preconditions.checkNotNull(emailAddress), name);
            return this;
        }

        /**
         * Sets Subject for an email message.
         *
         * @param <p>subject subject for email message.
         * <ul>
         * <li>Value can be empty , if no subject provided for email.</li>
         * </ul>
         * </p>
         * @return this builder for method chaining.
         * @exception NullPointerException Returns error if calling a method on a null reference(subject) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @NonNull
        public Builder setSubject(@Nullable final String subject) {
            mSubject = subject;
            return this;
        }

        /**
         * Sets text content for an email message.
         *
         * @param <p>message email message body text.
         * <ul>
         * <li>Value can be empty , if no message provided for email body.</li>
         * </ul>
         * </p>
         * @return this builder for method chaining.
         * @exception NullPointerException Returns error if calling a method on a null reference(message) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @NonNull
        public Builder setMessage(@Nullable final String message) {
            mMessage = message;
            return this;
        }

        /**
         * Adds files as email attachments.
         *
         * @param <p>files array of email attachment files.
         * <ul>
         * <li>Value can be empty , if no attachment provided for email.</li>
         * </ul>
         * </p>
         * @return Builder builder with attachments.
         * @exception NullPointerException Returns error if calling a method on a null reference(files) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @NonNull
        public Builder addAttachments(@NonNull final File... files) {
            mAttachmentList.addAll(Arrays.asList(files));
            return this;
        }

        /**
         * Combines all of the attributes in this into a {@link EmailAttributes} object.
         *
         * @return a {@link EmailAttributes} object containing all of the attributes.
         * @exception CapabilitiesExceededException if attributes are out of supported range.
         * @since API 1
         */
        @SuppressWarnings("unused")
        @NonNull
        public EmailAttributes build() throws CapabilitiesExceededException {
            if (mFrom != null && !CommonUtility.isValidEmail(mFrom.getAddress())) {
                throw new CapabilitiesExceededException("Email FROM value is not valid");
            }

            if (mToList != null) {
                for (EmailAddressInfo emailAddressInfo : mToList) {
                    if (emailAddressInfo == null || !CommonUtility.isValidEmail(emailAddressInfo.getAddress())) {
                        throw new CapabilitiesExceededException("Email TO values are not valid");
                    }
                }
            }

            if (mCcList != null) {
                for (EmailAddressInfo emailAddressInfo : mCcList) {
                    if (emailAddressInfo == null || !CommonUtility.isValidEmail(emailAddressInfo.getAddress())) {
                        throw new CapabilitiesExceededException("Email CC values are not valid");
                    }
                }
            }

            if (mBccList != null) {
                for (EmailAddressInfo emailAddressInfo : mBccList) {
                    if (emailAddressInfo == null || !CommonUtility.isValidEmail(emailAddressInfo.getAddress())) {
                        throw new CapabilitiesExceededException("Email BCC values are not valid");
                    }
                }
            }

            return new EmailAttributes(this);
        }
    }

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public String toString() {
        return new StringBuilder().append("[").append("From=").append(((mFrom != null && mFrom.getAddress() != null) ? mFrom.getAddress().toString() : "null")).append("]").toString();
    }
}
