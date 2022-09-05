package com.example.petpj.controller.board;

//Date는 시간을 호출하는 내장 라이브러리를 호출하는것(import)
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//외장 라이브러리 호출(import)
//gradle로 설치한 라이브러리 이다.
import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.Entity.board.Board;
import com.example.petpj.Entity.data.FileUploadEntity;
import com.example.petpj.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

import static com.google.common.io.ByteStreams.toByteArray;

/*0831
* 롬북 log 어노테이션*/
@Slf4j
@Controller
public class BoardController {

    //p.327~328
    //@리퀘스트 매핑은 [서버]에서 디스페처 서블릿을 통해 [클라이언트]html의 action태그의 주소와 동일한
    //문자열을 찾는 매핑기능(연결)이 실행되고 하단에 매서드가 실행된다.

    /*
     * * Board domain CONTROLLER
     * @Param String HTML에서 받아온 데이터
     * @return String HTML 파일과 연결을 위해 사용(ViewResolber)
     * @ author(작성자) 김명훈
     * @version 20220808.0.0.1
     */

    private final BoardService boardService;

    @Autowired
    protected BoardController(BoardService boardService){
        this.boardService = boardService;
    }
//    --------------------------------getBoardList---------------------------------
    @RequestMapping("getBoardList")
    public String getBoardList(Model model, Board board) {
        //List 타입으로 Board객체를 넣는 boardList 변수명 선언
        // = (대입연산자)로 heap메모리에 ArrayList 타입으로 할당
        //List는 ArrayList의 부모클래스 이다

        List<Board> boardList = boardService.getBoardList(board);
        model.addAttribute("boardList", boardList);
        //디서패처 서블릿이 뷰 리저버가 찾아서 연결해 준다.
        //viewResolver 가 같은 name의 값을 찾아준다
        //return값이 String 인 이유는 뷰 리저버가 html파일을 찾기 위한 문자열을 리턴.
        return "getBoardList";
    }

    //@GetMapping 또는 @PostMapping 은 @RequestMapping의 자식 클래스
    //@RequestMapping의 기능을 모두 사용 가능하다.(자식클래스 @ 이 아닌 부모클래스 @을 쓰는 이유는 기능의 한정을 통해
    //서버의 리소스 감소 및 보안을 위해서 이다.
    //p.p40
//    --------------------------------insertBoard---------------------------------
    @GetMapping("insertBoard")
    public String inertBoard() {
        System.out.println("----------GET insertBoard----------");
        return "insertBoard";
    }
//0901
    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @Nullable@RequestParam("uploadfile")MultipartFile[] uploadfile) {
        //@Nullable@RequestParam("uploadfile") MultipartFile[]:
        //MultipartFile을 클라이언트에서 받아오고, 데이터가 없더라도 허용(@Nullable)
//        System.out.println("----------POST insertBoard----------");
//        board.setCreateDate(new Date());
//        boardService.insertBoard(board);
//        return "redirect:getBoardList";
        try {
            //boardService.insertBoard 메서드에서는 DFB에 데이터를 저장하고 저장된 board_seq를 리턴 받음
            Long board_seq = boardService.insertBoard(board);
            List<FileUploadEntity> list = new ArrayList<>();
            for (MultipartFile file : uploadfile) {
                //MultipartFile로 클라이언트에서 온 데이터가 무결성 조건에 성립을 안하거나 메타 데이터가 없거나 문제가 생길
//                여지를 if문으로 처리
                if (!file.isEmpty()) {
                    FileUploadEntity entity = new FileUploadEntity(null,
                            UUID.randomUUID().toString(),
                            file.getContentType(),
                            file.getName(),
                            file.getOriginalFilename(),board_seq
                    );
                    boardService.insertFileUploadEntity(entity);
//                    log.info("seq check!");
//                    log.info(output.toString());
                    list.add(entity);
                    File newFileName = new File(entity.getUuid() + "_" + entity.getOriginalFilename());
                    file.transferTo(newFileName);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return "redirect:/getBoardList";
    }
//0901 업데이트
    //    --------------------------------getBoard---------------------------------
    @RequestMapping("getBoard")
    public String getBoard(Board board, Model model) {
        System.out.println("----------REQUEST getBoard----------");
        FileUploadEntity fileUploadEntity = boardService.getFileUploadEntity2(board.getSeq());
        String path = "/image/"+fileUploadEntity.getUuid()+"_"+fileUploadEntity.getOriginalFilename();

        model.addAttribute("boardList", boardService.getBoard(board));
        model.addAttribute("imgLoading", path);
        return "getBoard";
    }
    //    --------------------------------deleteBoard---------------------------------
    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("----------GET deleteBoard----------");
        boardService.deleteBoard(board);
        return "redirect:getBoardList";
    }
    //    --------------------------------updateBoard---------------------------------
    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("----------POST updateBoard----------");
        boardService.updateBoard(board);
        return "redirect:getBoardList";
    }
    //    --------------------------------commentInput---------------------------------
    @PostMapping("commentInput")
    public String comment(Model model, Board board){
        System.out.println("----------POST commnetInput----------");
//        List<Board> boardListC = boardService.category(board);
//        model.addAttribute("boardListC", boardListC);
        boardService.comment(board);
        model.addAttribute("boardListC", boardService.comment(board));
        return "redirect:getBoardList";
    }
    //    --------------------------------selectBoard---------------------------------
    // 0822
    @GetMapping("/selectBoard")
    public String selectBoard(Member member, Model model) {
        System.out.println("----------GET selectBoard----------");
        //board.getId()는 클라이언트 에서 가져왔다

        //@Service클래스에 board를 인자값 으로 넣고 메서드를 실행해야 한다.
        model.addAttribute("boardList", boardService.getBoardListByMemberId(member));

        // 회원이 작성한 게시글 리스트(List<Board>)를 HTML에 뿌려주면 끝난다(Controller에 가면 메서드가 실행되서
        // 다른 결과물을 리턴받기 때문에 어느 HTML로 가느냐??
        // 객체지향은 재활용성이 중요하므로 HTML 중에 재사용 할만한 것을 먼저 찾아보자.
        // return 페이지 Or controller Mapping
        return "redirect:getBoardList";
    }
    //    --------------------------------uploadfileBoard---------------------------------
    /*0831
    client에서 server로 이미지파일 전송(데이터 전송)
    html form태그에 upload버튼으로 이미지 데이터 전송(MultipartFile) > Entity기준으로 데이터 정보 전달

    1)server는 이미지 파일을 특정 폴더에 저장시
    장점
    서버에 원본 이미지 파일을 저장하므로 필요할때 서버에서 바로 전달 받을 수 있음
    db 에 부담감이 줄어듬
    단점
    다수의 서버에 이미지 파일을 저장할 경우, 동일한 이미지 데이터 처리에 대한 이슈 발생
    UUID(16bit기준으로 동일한 그림 처리)를 통해 이미지 이름을 구분한다.

    2)server는 이미지 파일을 byte코드로 db에 저장시
    장점
    이미지 데이터를 한 곳에 저장하고 관리가능
    단점
    DB에 많은 부하가 걸림, 데이터 저장 포맷의 한계(oracle 기준으로 Blob단위로 저장할때 4gb한계에 대한 이슈)*/

    //0831
//    @PostMapping("/uploadFile")
//    public String uploadFile(@RequestParam("uploadfile")MultipartFile[] uploadfile,
//            @RequestParam("writer")String input_writer) throws IOException {
//        //multipartfile을 클라이언트에서 서버로 RequestParam데이터를 받아옴 name=upladfile
//        System.out.println("----------POST uploadFile----------");
//        //@Slf4j Lombook라이브러리로 log데이터 찍음
//        //info /error/debug 단위가 있고 단위마다 필터링해 정보를 수집하고 관리 가능
//        log.info("img load session");
//        //multipartfile데이터를 수집해 entity FileUploadEntity에 데이터 저장
//        List<FileUploadEntity> list = new ArrayList<>();
//        for(MultipartFile file : uploadfile){
//            //MultipartFile이 있을 때까지 작업 진행
//            if(!file.isEmpty()){
//                //MultipartFile의 정보를 dto에 저장
//                //file.get~ 메서드는 MultipartFile(이미지) 내부에 있는 메타데이터를 가져오는 메서드
//                //input_writer는 클라이언트에서 데이터를 직접 전달하는 String데이터
//                FileUploadEntity entity = new FileUploadEntity(null, UUID.randomUUID().toString(),
//                        file.getContentType(),file.getName(),file.getOriginalFilename(), input_writer);
//                Long output = boardService.insertFileUploadEntity(entity);
//                log.info("seq check!");
//                log.info(output.toString());
//                list.add(entity);
//                //upload폴더에 저장되는 파일명 설정
//                File newFileName = new File(entity.getUuid()+ "_" + entity.getOriginalFilename());
//                //file을 서버에 저장하는 스트림행위는 서버가 성공에 대한 여부를 체크하므로 exception처리 필요
//                //메서드에 throws IOException 처리 = try catch처리 필요
//                file.transferTo(newFileName);
//            }
//        }
//        return "redirect:getBoardList";
//    }
    /*0831
    server에서 client로 이미지 전송
    springBoot에서 URL주소를 통해 이미지를 받음, inputStream을 통해 파일을 http프로토콜에 전달하여
    클라이언트에게 전송 */

    /*ResponseEntity
    http 프로토콜을 통해서 byte데이터를 전달하는 객체, byte(소문자 = 기본타입) []배열
    @PathVariable
    URL주소의 값을 받아옴 */
//    @GetMapping("/viewImage/{imgname}")
//    public ResponseEntity<byte[]> viewImage(@PathVariable("imgname")String input_imgName) throws IOException {
////      데이터(이미지)를 전송하기 윈한 객체로써 java에서는 항상 데이터를 스트림타입으로 절달한다.
//        String path = "C:/Users/admin/Desktop/PetPj/src/main/resources/static/tool/upload" + input_imgName;
//        InputStream inputStream = new FileInputStream(path);
//        //byte배열로 변환
//        byte[] imgByteArr = toByteArray(inputStream);
//        inputStream.close();
//        //ResponseEntity를 통해 http프로토콜로 클라이언트에게 데이터 전송
//        return new ResponseEntity<byte[]>(imgByteArr, HttpStatus.OK);
//    }
    //0901
    @GetMapping(value = "/image/{imgname}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> imageLoading(@PathVariable("imgname")String imgname) throws IOException{
        //ResponseEntity<byte[]> : 메서드 리턴타입으로 이미지 데이터를 송신하기 위한 객체 <바이트 배열>
        //throws IOException : 스트림방식으로 데이터를 전송할 때 도중에 오류가 날 경우를 찾기 위해서 선언한 Exception

        String path = "C:/Users/admin/Desktop/PetPj/src/main/resources/static/tool/upload/" + imgname;
     /*File을 컴퓨터가 이해하기 위해서 Stream 배열을 만들어서 작업
     객체(데이터 저장) : String, int, double
     Stream객체는 파일을 컴퓨터가 cpu에서 바로 읽어들일 수 있도록 하는 객체
        InputStream inputStream = new FileInputStream(path);
        byte배열로 변환*/
        FileInputStream fis = new FileInputStream(path);
        //Buffered : CPU에서 데이터를 읽어올 때 메모리와 캐시 사이에서 CPU와 속도 차이를 줄이기 위한 중간 저장 위치
        BufferedInputStream bis = new BufferedInputStream(fis);
        //byte배열로 전환해 ResponseEntity를 통해 클라이언트에 데이터 전달
        //HTTP프로토콜은 바이트 단위[배열]로 데이터를 주고 받음
        byte[] imgByteArr = bis.readAllBytes();
        //ResponseEntity를 통해 http프로토콜로 클라이언트에게 데이터 전송
        //http 프로토콜은 바이트 배열로 데이터를 주고 받기 때문에 stream이나 버퍼를 통해 전환.
        return new ResponseEntity<byte[]>(imgByteArr, HttpStatus.OK);

    }
}