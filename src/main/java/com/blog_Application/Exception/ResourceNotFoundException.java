package com.blog_Application.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public
class ResourceNotFoundException extends RuntimeException
{

    String ResourceName;
    String Resourcefield;
    String value;
    public ResourceNotFoundException(String ResourceName, String value, String Resourcefield)
    {
        super(String.format("%s %s : %s",ResourceName,Resourcefield,value));
        this.ResourceName=ResourceName;
        this.Resourcefield=Resourcefield;
        this.value=value;
    }

    public ResourceNotFoundException(String ResourceName, String value)
    {
        super(String.format("%s %s ",ResourceName,value));
        this.ResourceName=ResourceName;
        this.value=value;
    }

}
