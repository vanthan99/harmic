package vanthan99.harmic.services;

import vanthan99.harmic.payloads.request.UserRequest;
import vanthan99.harmic.payloads.response.MessageResponse;

public interface UserService {
    MessageResponse createNewAccountMember(UserRequest userRequest);
}
