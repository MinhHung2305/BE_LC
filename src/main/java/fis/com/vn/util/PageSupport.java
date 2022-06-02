package fis.com.vn.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class PageSupport<T> {
    public static final String FIRST_PAGE_NUM = "0";
    public static final String DEFAULT_PAGE_SIZE = "1000000";
    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;

    @JsonProperty
    public long totalPages() {
        return this.pageSize > 0 ? (this.totalElements - 1L) / (long)this.pageSize + 1L : 0L;
    }

    @JsonProperty
    public boolean first() {
        return this.pageNumber == Integer.parseInt("0");
    }

    @JsonProperty
    public boolean last() {
        return (long)((this.pageNumber + 1) * this.pageSize) >= this.totalElements;
    }

    public PageSupport(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return this.content;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageSupport)) {
            return false;
        } else {
            PageSupport other;
            label32: {
                other = (PageSupport)o;
                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content == null) {
                        break label32;
                    }
                } else if (this$content.equals(other$content)) {
                    break label32;
                }

                return false;
            }

            if (this.getPageNumber() != other.getPageNumber()) {
                return false;
            } else if (this.getPageSize() != other.getPageSize()) {
                return false;
            } else {
                return this.getTotalElements() == other.getTotalElements();
            }
        }
    }

    public int hashCode() {
        int result = 1;
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        result = result * 59 + this.getPageNumber();
        result = result * 59 + this.getPageSize();
        long $totalElements = this.getTotalElements();
        result = result * 59 + (int)($totalElements >>> 32 ^ $totalElements);
        return result;
    }

    public String toString() {
        List var10000 = this.getContent();
        return "PageSupport(content=" + var10000 + ", pageNumber=" + this.getPageNumber() + ", pageSize=" + this.getPageSize() + ", totalElements=" + this.getTotalElements() + ")";
    }
}