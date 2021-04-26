package goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.GroupDataMapper;
import goal.vo.GroupDataVO;

@Service
@Component
public class GroupDataServiceImpl implements GroupDataService{
	
	@Autowired
	private GroupDataMapper groupDataMapper;
	
	@Override
	public void insertData(GroupDataVO groupData) {
		groupDataMapper.insertData(groupData);
	}
	
}
