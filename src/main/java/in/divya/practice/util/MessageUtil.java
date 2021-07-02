
 
package in.divya.practice.util;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;


/**
 * @author divy2624
 *
 */

@JsonInclude(value = Include.NON_NULL)
@Data
@Component
public class MessageUtil {

	
	private String errorMessage;
	private String infoMessage;

}
