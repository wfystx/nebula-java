/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vesoft.nebula.storage;

import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.BitSet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.facebook.thrift.*;
import com.facebook.thrift.async.*;
import com.facebook.thrift.meta_data.*;
import com.facebook.thrift.server.*;
import com.facebook.thrift.transport.*;
import com.facebook.thrift.protocol.*;

@SuppressWarnings({ "unused", "serial" })
public class ScanTag implements TBase, java.io.Serializable, Cloneable, Comparable<ScanTag> {
  private static final TStruct STRUCT_DESC = new TStruct("ScanTag");
  private static final TField TAG_ID_FIELD_DESC = new TField("tagId", TType.I32, (short)1);
  private static final TField KEY_FIELD_DESC = new TField("key", TType.STRING, (short)2);
  private static final TField VALUE_FIELD_DESC = new TField("value", TType.STRING, (short)3);

  public int tagId;
  public byte[] key;
  public byte[] value;
  public static final int TAGID = 1;
  public static final int KEY = 2;
  public static final int VALUE = 3;
  public static boolean DEFAULT_PRETTY_PRINT = true;

  // isset id assignments
  private static final int __TAGID_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<Integer, FieldMetaData> metaDataMap;
  static {
    Map<Integer, FieldMetaData> tmpMetaDataMap = new HashMap<Integer, FieldMetaData>();
    tmpMetaDataMap.put(TAGID, new FieldMetaData("tagId", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMetaDataMap.put(KEY, new FieldMetaData("key", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.STRING)));
    tmpMetaDataMap.put(VALUE, new FieldMetaData("value", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMetaDataMap);
  }

  static {
    FieldMetaData.addStructMetaDataMap(ScanTag.class, metaDataMap);
  }

  public ScanTag() {
  }

  public ScanTag(
    int tagId,
    byte[] key,
    byte[] value)
  {
    this();
    this.tagId = tagId;
    setTagIdIsSet(true);
    this.key = key;
    this.value = value;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ScanTag(ScanTag other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.tagId = TBaseHelper.deepCopy(other.tagId);
    if (other.isSetKey()) {
      this.key = TBaseHelper.deepCopy(other.key);
    }
    if (other.isSetValue()) {
      this.value = TBaseHelper.deepCopy(other.value);
    }
  }

  public ScanTag deepCopy() {
    return new ScanTag(this);
  }

  @Deprecated
  public ScanTag clone() {
    return new ScanTag(this);
  }

  public int  getTagId() {
    return this.tagId;
  }

  public ScanTag setTagId(int tagId) {
    this.tagId = tagId;
    setTagIdIsSet(true);
    return this;
  }

  public void unsetTagId() {
    __isset_bit_vector.clear(__TAGID_ISSET_ID);
  }

  // Returns true if field tagId is set (has been assigned a value) and false otherwise
  public boolean isSetTagId() {
    return __isset_bit_vector.get(__TAGID_ISSET_ID);
  }

  public void setTagIdIsSet(boolean value) {
    __isset_bit_vector.set(__TAGID_ISSET_ID, value);
  }

  public byte[]  getKey() {
    return this.key;
  }

  public ScanTag setKey(byte[] key) {
    this.key = key;
    return this;
  }

  public void unsetKey() {
    this.key = null;
  }

  // Returns true if field key is set (has been assigned a value) and false otherwise
  public boolean isSetKey() {
    return this.key != null;
  }

  public void setKeyIsSet(boolean value) {
    if (!value) {
      this.key = null;
    }
  }

  public byte[]  getValue() {
    return this.value;
  }

  public ScanTag setValue(byte[] value) {
    this.value = value;
    return this;
  }

  public void unsetValue() {
    this.value = null;
  }

  // Returns true if field value is set (has been assigned a value) and false otherwise
  public boolean isSetValue() {
    return this.value != null;
  }

  public void setValueIsSet(boolean value) {
    if (!value) {
      this.value = null;
    }
  }

  public void setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
    case TAGID:
      if (value == null) {
        unsetTagId();
      } else {
        setTagId((Integer)value);
      }
      break;

    case KEY:
      if (value == null) {
        unsetKey();
      } else {
        setKey((byte[])value);
      }
      break;

    case VALUE:
      if (value == null) {
        unsetValue();
      } else {
        setValue((byte[])value);
      }
      break;

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  public Object getFieldValue(int fieldID) {
    switch (fieldID) {
    case TAGID:
      return new Integer(getTagId());

    case KEY:
      return getKey();

    case VALUE:
      return getValue();

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  // Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
  public boolean isSet(int fieldID) {
    switch (fieldID) {
    case TAGID:
      return isSetTagId();
    case KEY:
      return isSetKey();
    case VALUE:
      return isSetValue();
    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ScanTag)
      return this.equals((ScanTag)that);
    return false;
  }

  public boolean equals(ScanTag that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_tagId = true;
    boolean that_present_tagId = true;
    if (this_present_tagId || that_present_tagId) {
      if (!(this_present_tagId && that_present_tagId))
        return false;
      if (!TBaseHelper.equalsNobinary(this.tagId, that.tagId))
        return false;
    }

    boolean this_present_key = true && this.isSetKey();
    boolean that_present_key = true && that.isSetKey();
    if (this_present_key || that_present_key) {
      if (!(this_present_key && that_present_key))
        return false;
      if (!TBaseHelper.equalsSlow(this.key, that.key))
        return false;
    }

    boolean this_present_value = true && this.isSetValue();
    boolean that_present_value = true && that.isSetValue();
    if (this_present_value || that_present_value) {
      if (!(this_present_value && that_present_value))
        return false;
      if (!TBaseHelper.equalsSlow(this.value, that.value))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_tagId = true;
    builder.append(present_tagId);
    if (present_tagId)
      builder.append(tagId);

    boolean present_key = true && (isSetKey());
    builder.append(present_key);
    if (present_key)
      builder.append(key);

    boolean present_value = true && (isSetValue());
    builder.append(present_value);
    if (present_value)
      builder.append(value);

    return builder.toHashCode();
  }

  @Override
  public int compareTo(ScanTag other) {
    if (other == null) {
      // See java.lang.Comparable docs
      throw new NullPointerException();
    }

    if (other == this) {
      return 0;
    }
    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTagId()).compareTo(other.isSetTagId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(tagId, other.tagId);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetKey()).compareTo(other.isSetKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(key, other.key);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetValue()).compareTo(other.isSetValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(value, other.value);
    if (lastComparison != 0) {
      return lastComparison;
    }
    return 0;
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin(metaDataMap);
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id)
      {
        case TAGID:
          if (field.type == TType.I32) {
            this.tagId = iprot.readI32();
            setTagIdIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case KEY:
          if (field.type == TType.STRING) {
            this.key = iprot.readBinary();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case VALUE:
          if (field.type == TType.STRING) {
            this.value = iprot.readBinary();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
          break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();


    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    oprot.writeFieldBegin(TAG_ID_FIELD_DESC);
    oprot.writeI32(this.tagId);
    oprot.writeFieldEnd();
    if (this.key != null) {
      oprot.writeFieldBegin(KEY_FIELD_DESC);
      oprot.writeBinary(this.key);
      oprot.writeFieldEnd();
    }
    if (this.value != null) {
      oprot.writeFieldBegin(VALUE_FIELD_DESC);
      oprot.writeBinary(this.value);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    return toString(DEFAULT_PRETTY_PRINT);
  }

  @Override
  public String toString(boolean prettyPrint) {
    return toString(1, prettyPrint);
  }

  @Override
  public String toString(int indent, boolean prettyPrint) {
    String indentStr = prettyPrint ? TBaseHelper.getIndentedString(indent) : "";
    String newLine = prettyPrint ? "\n" : "";
String space = prettyPrint ? " " : "";
    StringBuilder sb = new StringBuilder("ScanTag");
    sb.append(space);
    sb.append("(");
    sb.append(newLine);
    boolean first = true;

    sb.append(indentStr);
    sb.append("tagId");
    sb.append(space);
    sb.append(":").append(space);
    sb.append(TBaseHelper.toString(this. getTagId(), indent + 1, prettyPrint));
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("key");
    sb.append(space);
    sb.append(":").append(space);
    if (this. getKey() == null) {
      sb.append("null");
    } else {
        int __key_size = Math.min(this. getKey().length, 128);
        for (int i = 0; i < __key_size; i++) {
          if (i != 0) sb.append(" ");
          sb.append(Integer.toHexString(this. getKey()[i]).length() > 1 ? Integer.toHexString(this. getKey()[i]).substring(Integer.toHexString(this. getKey()[i]).length() - 2).toUpperCase() : "0" + Integer.toHexString(this. getKey()[i]).toUpperCase());
        }
        if (this. getKey().length > 128) sb.append(" ...");
    }
    first = false;
    if (!first) sb.append("," + newLine);
    sb.append(indentStr);
    sb.append("value");
    sb.append(space);
    sb.append(":").append(space);
    if (this. getValue() == null) {
      sb.append("null");
    } else {
        int __value_size = Math.min(this. getValue().length, 128);
        for (int i = 0; i < __value_size; i++) {
          if (i != 0) sb.append(" ");
          sb.append(Integer.toHexString(this. getValue()[i]).length() > 1 ? Integer.toHexString(this. getValue()[i]).substring(Integer.toHexString(this. getValue()[i]).length() - 2).toUpperCase() : "0" + Integer.toHexString(this. getValue()[i]).toUpperCase());
        }
        if (this. getValue().length > 128) sb.append(" ...");
    }
    first = false;
    sb.append(newLine + TBaseHelper.reduceIndent(indentStr));
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check that fields of type enum have valid values
  }

}

