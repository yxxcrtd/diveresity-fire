package com.hublotcloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hublotcloud.domain.Atachment;
import com.hublotcloud.service.AtachmentService;
import com.hublotcloud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Atachment Controller
 */
@Api(tags = "附件管理")
@RestController
@RequestMapping("biz/atachment")
public class AtachmentController {

    @Autowired
    private AtachmentService atachmentService;

    /**
     * List
     */
    @ApiOperation(value = "附件列表")
    @GetMapping("list")
    JsonResult<Object> list(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(p - 1, size, sort);
        return JsonResult.jsonResultSuccess("获取附件列表", atachmentService.findAllWithPage(request, pageable, k));
    }

    /**
     * Edit
     */
    @ApiOperation(value = "附件详情")
    @GetMapping("edit")
    JsonResult<Object> edit(@RequestParam Long id) {
        Atachment atachment;
        if (0 == id) {
            atachment = new Atachment();
            atachment.setId(id);
        } else {
            atachment = atachmentService.getById(id);
        }
        return JsonResult.jsonResultSuccess("编辑或查看附件详情", atachment);
    }

    /**
     * Save
     */
    @ApiOperation(value = "附件保存")
    @PostMapping("save")
    JsonResult<Object> save(@ModelAttribute("atachment") @Valid Atachment atachment) {
        return atachmentService.save(atachment);
    }

}
