package DTO_Tuto;

public class UserDTOMapper {
	public static UserDTO mapUserToDTO(User user) {
		Address address = user.getAddress();
		return new UserDTO(user.getId(),
				user.getUsername(), 
				user.getEmail(), 
				address.getState(), 
				address.getZipCode());
	}

}
